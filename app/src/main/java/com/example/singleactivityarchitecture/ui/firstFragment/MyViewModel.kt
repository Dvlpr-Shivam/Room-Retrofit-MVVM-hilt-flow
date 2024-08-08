package com.example.singleactivityarchitecture.ui.firstFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.singleactivityarchitecture.Network.DataRepository
import com.example.singleactivityarchitecture.Network.DataResult
import com.example.singleactivityarchitecture.dataBase.table.Poster
import com.example.singleactivityarchitecture.dataBase.table.MyData
import com.example.singleactivityarchitecture.di.MyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(private val repository: MyRepository,private val net:DataRepository) : ViewModel() {

    private val _allData = MutableStateFlow<List<MyData>>(emptyList())
    val allData: StateFlow<List<MyData>> = _allData

    private val _allDataPoster = MutableStateFlow<List<Poster>>(emptyList())
    val allDataPoster: StateFlow<List<Poster>> = _allDataPoster

    private val _data = MutableStateFlow<DataResult<List<Poster>>?>(null)
    val data: StateFlow<DataResult<List<Poster>>?> = _data

    init {

        viewModelScope.launch {
            repository.allData.collect {
                _allData.value = it
            }
        }
        viewModelScope.launch {
            repository.allPoster.collect {
                _allDataPoster.value = it
                if (it.isEmpty()){
                    fetchData()
                }
                else{
                    _data.value = DataResult.Success(it)
                }
            }
        }
    }

    fun insert(data: MyData) = viewModelScope.launch {
        repository.insert(data)
    }
    private fun fetchData() {
        viewModelScope.launch {
            net.getPosterData().collect {
                _data.value = it
                when(it){
                    is DataResult.Success->{
                        repository.insertPosterData(it.data)
                    }
                    else->{

                    }
                }
            }
        }
    }
}