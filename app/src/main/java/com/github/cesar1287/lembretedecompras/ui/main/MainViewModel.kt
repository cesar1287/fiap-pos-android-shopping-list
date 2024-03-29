package com.github.cesar1287.lembretedecompras.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.github.cesar1287.lembretedecompras.dao.ShoppingListRoomDatabase
import com.github.cesar1287.lembretedecompras.model.Product
import com.github.cesar1287.lembretedecompras.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val productRepository: ProductRepository

    val products: LiveData<List<Product>>

    init {
        val productDAO = ShoppingListRoomDatabase.getDatabase(application).productDAO()
        productRepository = ProductRepository(productDAO)
        products = productRepository.products
    }

    fun insert(product: Product) =
        viewModelScope.launch(Dispatchers.IO) {
            productRepository.insert(product)
        }

    fun delete(product: Product) =
        viewModelScope.launch(Dispatchers.IO) {
            productRepository.delete(product)
        }

    fun delete(productName: String) =
        viewModelScope.launch(Dispatchers.IO) {
            productRepository.deleteByProductName(productName)
        }

    fun deleteAll() =
        viewModelScope.launch(Dispatchers.IO) {
            productRepository.deleteAll()
        }
}
