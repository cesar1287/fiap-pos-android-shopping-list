package com.github.cesar1287.lembretedecompras.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.cesar1287.lembretedecompras.databinding.ProductItemBinding
import com.github.cesar1287.lembretedecompras.model.Product

class MainListAdapter(
    private val onProductDeleted: (Product) -> Unit
) : ListAdapter<Product, MainViewHolder>(Product.DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding =
            ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(getItem(position), onProductDeleted)
    }
}

class MainViewHolder(
    private val binding: ProductItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        product: Product,
        onProductDeleted: (Product) -> Unit
    ) {
        with(binding) {
            tvProduct.text = product.name

            ivDelete.setOnClickListener {
                onProductDeleted(product)
            }
        }
    }
}

