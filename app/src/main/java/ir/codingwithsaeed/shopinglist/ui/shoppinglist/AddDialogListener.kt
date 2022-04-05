package ir.codingwithsaeed.shopinglist.ui.shoppinglist

import ir.codingwithsaeed.shopinglist.data.entities.ShoppingItem

interface AddDialogListener {
    fun whenAdd(item: ShoppingItem)
}