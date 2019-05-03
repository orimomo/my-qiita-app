package com.example.myqiitaapp.data

import retrofit2.Retrofit

class ItemRepository(private val retrofit: Retrofit) {
    private val service by lazy { retrofit.create(ItemService::class.java) }
}
