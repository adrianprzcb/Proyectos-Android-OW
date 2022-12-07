package com.openwebinars.filmapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openwebinars.filmapp.model.FavoritesRepository
import com.openwebinars.filmapp.model.Film
import kotlinx.coroutines.launch

class FavsViewModel: ViewModel() {

    val favsLiveData = MutableLiveData<List<Film>>()
    val isLoading = MutableLiveData<Boolean>()
    private val favsRepository: FavoritesRepository = FavoritesRepository()

    fun onViewCreated() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val favs = favsRepository.get()
            favsLiveData.postValue(favs)
            isLoading.postValue(false)
        }
    }
}