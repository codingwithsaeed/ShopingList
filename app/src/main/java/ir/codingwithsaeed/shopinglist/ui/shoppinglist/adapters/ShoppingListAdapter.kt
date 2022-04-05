package ir.codingwithsaeed.shopinglist.ui.shoppinglist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.codingwithsaeed.shopinglist.R
import ir.codingwithsaeed.shopinglist.data.entities.ShoppingItem
import ir.codingwithsaeed.shopinglist.ui.shoppinglist.ShoppingViewModel
import kotlinx.android.synthetic.main.shopping_item.view.*

class ShoppingListAdapter(
    var items: List<ShoppingItem>,
    private val viewModel: ShoppingViewModel
) : RecyclerView.Adapter<ShoppingListAdapter.ShoppingItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.shopping_item, parent, false)
        return ShoppingItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingItemViewHolder, position: Int) {
        val currentItem = items[position]
        holder.itemView.tvName.text = currentItem.name
        holder.itemView.tvAmount.text = "${currentItem.amount}"
        holder.itemView.ivDelete.setOnClickListener {
            viewModel.delete(currentItem)
        }
        holder.itemView.ivPlus.setOnClickListener {
            currentItem.amount++
            viewModel.upsert(currentItem)
        }
        holder.itemView.ivMinus.setOnClickListener {
            if (currentItem.amount > 0) {
                currentItem.amount--
                viewModel.upsert(currentItem)
            }
        }
    }

    override fun getItemCount(): Int = items.size

    inner class ShoppingItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}