package com.github.cesar1287.lembretedecompras.repository

import androidx.lifecycle.LiveData
import com.github.cesar1287.lembretedecompras.dao.ProductDAO
import com.github.cesar1287.lembretedecompras.model.Product

class ProductRepository(
    private val productDAO: ProductDAO
) {

    val products: LiveData<List<Product>> = productDAO.getProducts()

    suspend fun insert(product: Product) {
        productDAO.insert(product)
    }
}
