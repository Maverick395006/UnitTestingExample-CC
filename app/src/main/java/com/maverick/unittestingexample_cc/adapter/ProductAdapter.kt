package com.maverick.unittestingexample_cc.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.maverick.unittestingexample_cc.R
import com.maverick.unittestingexample_cc.databinding.ProductItemLayoutBinding
import com.maverick.unittestingexample_cc.models.ProductListItem
import com.maverick.unittestingexample_cc.utils.setImageFromUrl

class ProductAdapter() :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private var productList = mutableListOf<ProductListItem>()

    interface EventListener {
        fun onItemClick(position: Int, item: ProductListItem)
    }

    private lateinit var mEventListener: EventListener

    fun setEventListener(eventListener: EventListener) {
        mEventListener = eventListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = DataBindingUtil.inflate<ProductItemLayoutBinding>(
            inflater,
            R.layout.product_item_layout,
            parent,
            false
        )
        return ProductViewHolder(itemBinding)

    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentItem = productList[position]
        try {
            holder.itemBinding.productName.text = currentItem.title
            holder.itemBinding.productImage.setImageFromUrl(currentItem.image!!)

            holder.itemBinding.root.setOnClickListener {
                mEventListener.onItemClick(position, currentItem)
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getItemCount(): Int = productList.size

    fun addAll(mData: List<ProductListItem>?) {
        productList.clear()
        productList.addAll(mData!!)
        notifyDataSetChanged()
    }

    inner class ProductViewHolder(internal var itemBinding: ProductItemLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

}