package com.ansh.mvvm

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="quote")
data class Quote(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val name:String,
    val author:String
)
