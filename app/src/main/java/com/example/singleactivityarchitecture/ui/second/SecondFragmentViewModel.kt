package com.example.singleactivityarchitecture.ui.second

import androidx.annotation.WorkerThread
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.singleactivityarchitecture.Network.DataRepository
import com.example.singleactivityarchitecture.dataBase.table.Poster
import com.example.singleactivityarchitecture.di.MyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SecondFragmentViewModel @Inject constructor(val repository: MyRepository):ViewModel() {
    private val _dataPoster = MutableStateFlow<Poster?>(null)
    val dataPoster: StateFlow<Poster?> = _dataPoster

    @WorkerThread
    fun getSelectiveItem(id:Long) : Flow<Poster> = flow {
        val data = repository.getItemById(id)
        emit(data)
       /* viewModelScope.launch {
            repository.getItemById(id).collect{
                _dataPoster.value = it
            }
        }*/


    }.flowOn(Dispatchers.IO)
}