package com.github.cesar1287.lembretedecompras.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class Product(

    @PrimaryKey
    @ColumnInfo(name = "product_name")
    val name: String
)
