package com.openwebinars.filmapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openwebinars.filmapp.model.Film
import com.openwebinars.filmapp.model.NewFilmsRepository
import kotlinx.coroutines.launch

class NewsViewModel: ViewModel() {

    val newsLiveData = MutableLiveData<List<Film>>()
    val isLoading = MutableLiveData<Boolean>()
    private val newFilmsRepository: NewFilmsRepository = NewFilmsRepository()

    fun getNews() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val news = newFilmsRepository.get()
            newsLiveData.postValue(news)
            isLoading.postValue(false)
        }
    }
}