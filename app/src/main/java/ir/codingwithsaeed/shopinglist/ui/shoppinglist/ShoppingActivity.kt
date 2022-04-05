package ir.codingwithsaeed.shopinglist.ui.shoppinglist

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ir.codingwithsaeed.shopinglist.R
import ir.codingwithsaeed.shopinglist.data.db.ShoppingDB
import ir.codingwithsaeed.shopinglist.data.entities.ShoppingItem
import ir.codingwithsaeed.shopinglist.data.repositories.ShoppingRepository
import ir.codingwithsaeed.shopinglist.ui.shoppinglist.adapters.ShoppingListAdapter
import kotlinx.android.synthetic.main.activity_shopping.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class ShoppingActivity : AppCompatActivity(), KodeinAware {
    override val kodein: Kodein by closestKodein()
    private val factory: ShoppingViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val viewModel = ViewModelProvider(this, factory)[ShoppingViewModel::class.java]

        val adapter = ShoppingListAdapter(listOf(), viewModel)
        rvShoppingItems.layoutManager = LinearLayoutManager(this)
        rvShoppingItems.adapter = adapter

        viewModel.getAllShoppingItems().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        fab.setOnClickListener {
            AddShoppingItemDialog(this, object : AddDialogListener {
                override fun whenAdd(item: ShoppingItem) {
                    viewModel.upsert(item)
                }
            }).show()
        }
    }
}