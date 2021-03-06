package com.jmb.mdpostres

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jmb.mdpostres.databinding.ItemCartBinding

class CartAdapter(private val list: Array<String>) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val viewBinding =
            ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is CartHolder -> holder.bind(list[position], position)
        }
    }

    override fun getItemCount() = list.size

    inner class CartHolder(private val binding: ItemCartBinding) :
        BaseViewHolder<String>(binding.root) {
        override fun bind(item: String, position: Int) {
            binding.tvName.text = item
        }

    }

}