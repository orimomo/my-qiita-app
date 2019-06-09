package com.example.my_qiita_app.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.my_qiita_app.data.entity.ArticleEntity
import com.example.my_qiita_app.databinding.FragmentListBinding
import com.google.android.material.snackbar.Snackbar
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import kotlin.coroutines.CoroutineContext

class ListFragment(private val tabName: String) : Fragment(), CoroutineScope {
    private lateinit var binding: FragmentListBinding
    private val viewModel by sharedViewModel<ListViewModel>()
    private val groupAdapter = GroupAdapter<ViewHolder>()
    private lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val itemDecoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        binding = FragmentListBinding.inflate(inflater, container, false)
        binding.recyclerView.adapter = groupAdapter
        binding.recyclerView.addItemDecoration(itemDecoration)
        val layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = layoutManager

        // TODO: 残り3件までスクロールしたら追加読み込みするようにしたい
        // タブによってフラグメント分けないといけないかも…
//        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                if (layoutManager.findLastVisibleItemPosition() >= layoutManager.itemCount - 4) {
//                    Snackbar.make(binding.root, "残り3件だよ！" , Snackbar.LENGTH_SHORT).show()
//                    launch {
//                        viewModel.more(args.member.id)
//                    }
//                }
//            }
//        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.status.observe(this, Observer { status ->
            when (status) {
                ListViewModel.Status.LOADING -> {
                    binding.progress.isVisible = true
                }
                ListViewModel.Status.COMPLETED -> {
                    binding.progress.isGone = true
                    if (tabName == "kotlin") {
                        showArticles(viewModel.kotlinArticles.value ?: emptyList())
                    }

                    if (tabName == "android") {
                        showArticles(viewModel.androidArticles.value ?: emptyList())
                    }

                    if (tabName == "swift") {
                        showArticles(viewModel.swiftArticles.value ?: emptyList())
                    }

                    if (tabName == "ios") {
                        showArticles(viewModel.iosArticles.value ?: emptyList())
                    }
                }
                ListViewModel.Status.FAILED -> {
                    binding.progress.isGone = true
                }
                else -> { }
            }
        })

        viewModel.message.observe(this, Observer { message ->
            Snackbar.make(binding.root, message , Snackbar.LENGTH_SHORT).show()
        })
    }

    private fun showArticles(articles: List<ArticleEntity>) {
        groupAdapter.clear() // recyclerViewを一度破棄する
        articles.forEach { article ->
            groupAdapter.add(ListItem(article))
        }
    }

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }

    override fun onDestroyView() {
        binding.recyclerView.addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(v: View?) = Unit

            override fun onViewDetachedFromWindow(v: View?) {
                binding.recyclerView.adapter = null
            }
        })
        super.onDestroyView()
    }
}