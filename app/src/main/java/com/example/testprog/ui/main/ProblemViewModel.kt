package com.example.testprog.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.testprog.data.IZabbixRepository
import com.example.testprog.data.ProblemZab
import com.example.testprog.data.ZabbixAPIRepositoryImp
import com.example.testprog.data.domain.TriggerZab
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okio.IOException

class ProblemViewModel (val repository: IZabbixRepository): ViewModel(){
    private val _loading = MutableStateFlow(false)
    val loading : Flow<Boolean> = _loading
    private val _problemZab : MutableSharedFlow<TriggerZab?> = MutableStateFlow(null)
    val problemZab : Flow<TriggerZab?> = _problemZab
    private val _error : MutableSharedFlow<String> = MutableSharedFlow()
    val error : Flow<String> = _error

    fun loadingProblemZabbix() {
        viewModelScope.launch {
            _loading.emit(true)
            try {
                _problemZab.emit(repository.loadProblemZab())
            } catch (exc:IOException) {
                _error.emit("Network error")
            }
            _loading.emit(false)
        }
    }


class ProblemViewModelFactory(val repository: IZabbixRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = ProblemViewModel(repository) as T
}
}