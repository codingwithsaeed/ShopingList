package ir.codingwithsaeed.shopinglist.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ir.codingwithsaeed.shopinglist.data.entities.ShoppingItem

@Database(
    entities = [ShoppingItem::class],
    version = 1
)
abstract class ShoppingDB : RoomDatabase() {
    abstract fun getShoppingDao(): ShoppingItemDAO

    companion object {
        @Volatile
        private var instance: ShoppingDB? = null
        private val LOCK = Any()
        private fun createDB(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ShoppingDB::class.java,
            "ShoppingDB.db"
        ).build()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDB(context).also { instance = it }
        }
    }
}