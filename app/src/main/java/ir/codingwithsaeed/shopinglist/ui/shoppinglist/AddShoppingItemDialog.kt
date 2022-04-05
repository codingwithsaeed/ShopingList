package ir.codingwithsaeed.shopinglist.ui.shoppinglist

import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import ir.codingwithsaeed.shopinglist.R
import ir.codingwithsaeed.shopinglist.data.entities.ShoppingItem
import kotlinx.android.synthetic.main.dialog_add_shopping_item.*
import kotlinx.android.synthetic.main.shopping_item.*

class AddShoppingItemDialog(context: Context, private var listener: AddDialogListener) :
    AppCompatDialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_add_shopping_item)
        tvAdd.setOnClickListener {
            val name = etName.text.toString()
            val amount = etAmount.text.toString()
            if (name.isEmpty() || amount.isEmpty()) {
                Toast.makeText(context, "Enter all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            listener.whenAdd(ShoppingItem(name, amount.toInt()))
            dismiss()
        }
        tvCancel.setOnClickListener {
            dismiss()
        }
    }
}