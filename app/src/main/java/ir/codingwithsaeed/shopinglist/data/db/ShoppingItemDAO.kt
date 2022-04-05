package ir.codingwithsaeed.shopinglist.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import ir.codingwithsaeed.shopinglist.data.entities.ShoppingItem

@Dao
interface ShoppingItemDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: ShoppingItem)

    @Delete
    suspend fun delete(item: ShoppingItem)

    @Query("SELECT * FROM shopping_item")
    fun getShoppingItems(): LiveData<List<ShoppingItem>>
}