package com.example.my_qiita_app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.my_qiita_app.R
import com.example.my_qiita_app.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {
    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }
    private val viewModel by viewModel<ArticleViewModel>()
    private lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
        binding.viewPager.adapter = ListPagerAdapter(supportFragmentManager)
        lifecycle.addObserver(viewModel)
    }

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }

    inner class ListPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        private val items = listOf("kotlin", "Android", "Swift", "iOS")
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
