
package com.example.android.marsphotos.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.marsphotos.network.FruitApi
import com.example.android.marsphotos.network.model.Fruit
import kotlinx.coroutines.launch

enum class FruitApiStatus { LOADING, ERROR, DONE }

class OverviewViewModel : ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<FruitApiStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<FruitApiStatus> = _status

    private val _fruits = MutableLiveData<List<Fruit>>(emptyList())

    // The external LiveData interface to the property is immutable, so only this class can modify
    val fruits: LiveData<List<Fruit>> = _fruits

    private val _totalFruits = MutableLiveData<Int>()
    val totalFruits: LiveData<Int> = _totalFruits


    init {
        getFruits()
    }


    private fun getFruits() {

        viewModelScope.launch {
            _status.value = FruitApiStatus.LOADING
            try {
                _fruits.value = FruitApi.retrofitService.getFruits()
                _totalFruits.value = _fruits.value?.size ?: 0
                _status.value = FruitApiStatus.DONE
            } catch (e: Exception) {
                _status.value = FruitApiStatus.ERROR
                _fruits.value = listOf()
                println("Error $e")
            }
        }
    }
}
