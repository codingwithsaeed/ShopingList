package ir.codingwithsaeed.shopinglist

import android.app.Application
import ir.codingwithsaeed.shopinglist.data.db.ShoppingDB
import ir.codingwithsaeed.shopinglist.data.repositories.ShoppingRepository
import ir.codingwithsaeed.shopinglist.ui.shoppinglist.ShoppingViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class ShoppingApplication : Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@ShoppingApplication))
        bind() from singleton { ShoppingDB(instance()) }
        bind() from singleton { ShoppingRepository(instance()) }
        bind() from provider { ShoppingViewModelFactory(instance()) }
    }
}