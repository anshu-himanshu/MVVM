package com.ansh.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val quoteRepository: QuoteRepository) : ViewModel() {

    var position: MutableLiveData<Int> = MutableLiveData<Int>()


    init {
        position.value = 0
    }


    fun getQuiz(): LiveData<List<Quote>> {
        return quoteRepository.getQuiz()
    }

    fun insertQuiz(quote: Quote) {
        viewModelScope.launch(Dispatchers.IO) {
            quoteRepository.insertQuiz(quote)
        }
    }

}