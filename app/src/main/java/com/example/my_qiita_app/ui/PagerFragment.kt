package com.example.my_qiita_app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.my_qiita_app.databinding.FragmentPagerBinding
import com.example.my_qiita_app.ui.list.ListFragment
import com.example.my_qiita_app.ui.list.ListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import kotlin.coroutines.CoroutineContext

class PagerFragment : Fragment(), CoroutineScope {

    private lateinit var binding: FragmentPagerBinding
    private val viewModel by sharedViewModel<ListViewModel>()
    private lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentPagerBinding.inflate(inflater, container, false)
        binding.viewPager.adapter = ListPagerAdapter(childFragmentManager)
        lifecycle.addObserver(viewModel)
        return binding.root
    }

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }

    inner class ListPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        private val items = listOf("kotlin", "android", "swift", "ios")
        override fun getItem(position: Int): Fragment {
            return ListFragment(items[position])
        }

        override fun getCount(): Int {
            return items.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return items[position]
        }
    }
}
