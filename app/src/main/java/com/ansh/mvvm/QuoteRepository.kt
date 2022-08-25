package com.ansh.mvvm

import androidx.lifecycle.LiveData

class QuoteRepository(private val quizDao: QuizDao) {

    fun getQuiz(): LiveData<List<Quote>>{
        return quizDao.getQuiz()
    }

    suspend fun insertQuiz(quote: Quote){
        quizDao.insertQuiz(quote)
    }

}