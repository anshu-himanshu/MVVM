package com.ansh.mvvm

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface QuizDao {

    @Query("SELECT * from quiz")
    fun getQuiz() : LiveData<List<Quote>>

    @Insert
    suspend fun insertQuiz(quote: Quote)

}