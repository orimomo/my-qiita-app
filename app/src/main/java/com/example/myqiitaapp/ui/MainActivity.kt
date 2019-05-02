package com.example.myqiitaapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.myqiitaapp.R
import com.example.myqiitaapp.ui.list.ListItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val groupAdapter = GroupAdapter<ViewHolder>()
        findViewById<RecyclerView>(R.id.recycler_view).adapter = groupAdapter

        val items = listOf("りんご", "みかん", "ぶどう")
        items.forEach { a ->
            groupAdapter.add(ListItem(a))
        }
    }
}
