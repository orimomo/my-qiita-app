package com.example.myqiitaapp.data

import retrofit2.Retrofit

class ItemRepository(private val retrofit: Retrofit) {
    private val service by lazy { retrofit.create(ItemService::class.java) }

    suspend fun getItems(page: String = "1", parPage: String = "10"): List<ItemEntity> {
        return service.getItems(page, parPage).await()
    }
}
