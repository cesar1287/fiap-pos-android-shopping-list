package com.github.cesar1287.lembretedecompras.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.cesar1287.lembretedecompras.model.Product

@Dao
interface ProductDAO {

    @Query("SELECT * from product ORDER BY product_name ASC")
    fun getProducts(): LiveData<List<Product>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(product: Product)

    @Query("DELETE FROM product")
    suspend fun deleteAll()

}