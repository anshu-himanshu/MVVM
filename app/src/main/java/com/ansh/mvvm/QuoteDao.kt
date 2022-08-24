package com.ansh.mvvm

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Query

interface QuoteDao {

    @Query("SELECT * from quote")
    fun getQuotes() : LiveData<List<Quote>>

    @Insert
    suspend fun insertQuote(quote:Quote)
}