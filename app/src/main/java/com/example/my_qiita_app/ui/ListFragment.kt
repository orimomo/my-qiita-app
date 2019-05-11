package com.example.my_qiita_app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.my_qiita_app.databinding.FragmentLsitBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import kotlin.coroutines.CoroutineContext

class ListFragment : Fragment(), CoroutineScope {
    private lateinit var binding: FragmentLsitBinding
    private val viewModel by sharedViewModel<ArticleViewModel>()
    private val groupAdapter = GroupAdapter<ViewHolder>()
    private lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLsitBinding.inflate(inflater, container, false)
        binding.recyclerView.adapter = groupAdapter

        viewModel.articles.observe(this, Observer { articles ->
            articles.forEach { article ->
                groupAdapter.add(ListItem(article))
            }
        })
        return binding.root
    }

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }
}