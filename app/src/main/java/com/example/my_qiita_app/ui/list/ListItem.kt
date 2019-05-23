package com.example.my_qiita_app.ui.list

import com.bumptech.glide.Glide
import com.example.my_qiita_app.R
import com.example.my_qiita_app.data.entity.ArticleEntity
import com.example.my_qiita_app.databinding.ItemListBinding
import com.xwray.groupie.databinding.BindableItem

class ListItem(private val article: ArticleEntity) : BindableItem<ItemListBinding>() {
    override fun getLayout(): Int = R.layout.item_list

    override fun bind(viewBinding: ItemListBinding, position: Int) {
        viewBinding.article = article

        Glide.with(viewBinding.root.context)
            .load(article.user.profileImageUrl)
            .into(viewBinding.imageView)
    }
}