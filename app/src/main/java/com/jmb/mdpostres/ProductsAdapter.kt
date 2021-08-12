package com.jmb.mdpostres

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jmb.mdpostres.databinding.ItemProductBinding

class ProductsAdapter(private val list: List<Product>, private val itemClickListener: OnClickListener) : RecyclerView.Adapter<BaseViewHolder<*>>() {
    interface OnClickListener{
        fun onClick(product: Product)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val viewBinding =
            ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is ViewHolder -> holder.bind(list[position], position)
        }
    }

    override fun getItemCount() = list.size

    inner class ViewHolder(private val binding: ItemProductBinding) :
        BaseViewHolder<Product>(binding.root) {
        override fun bind(item: Product, position: Int) {
            binding.tvName.text = item.name
            Glide.with(itemView).load(item.url).apply(RequestOptions().centerCrop())
                .into(binding.imgPhoto)
            binding.root.setOnClickListener {
                item.selected = !item.selected
                binding.root.isChecked = item.selected
                itemClickListener.onClick(item)
            }
        }

    }

}