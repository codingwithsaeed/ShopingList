package ir.codingwithsaeed.shopinglist.data.repositories

import ir.codingwithsaeed.shopinglist.data.db.ShoppingDB
import ir.codingwithsaeed.shopinglist.data.entities.ShoppingItem

class ShoppingRepository(
    private val db: ShoppingDB
) {
    suspend fun upsert(item: ShoppingItem) = db.getShoppingDao().upsert(item)
    suspend fun delete(item: ShoppingItem) = db.getShoppingDao().delete(item)
    fun getAllShoppingItems() = db.getShoppingDao().getShoppingItems()
}