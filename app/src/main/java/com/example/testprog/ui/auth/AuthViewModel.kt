package com.example.testprog.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.testprog.data.AuthParam
import com.example.testprog.data.IZabbixRepository
import com.example.testprog.data.domain.AuthZab
import com.example.testprog.data.ZabbixAPIRepositoryImp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okio.IOException

class AuthViewModel(val repository: IZabbixRepository):ViewModel() {
    private val _loading = MutableStateFlow(false)
    val loading : Flow<Boolean> = _loading
    private val _authZab : MutableSharedFlow<AuthZab?> = MutableStateFlow(null)
    val authZab : Flow<AuthZab?> = _authZab
    private val _error : MutableSharedFlow<String> = MutableSharedFlow()
    val error : Flow<String> = _error

    fun loadingAuthZabbix(authParam: AuthParam) {
        viewModelScope.launch {
            _loading.emit(true)
            try {
                _authZab.emit(repository.authZabbix(authParam))
            } catch (exc:IOException) {
                _error.emit("Network error")
            }
            _loading.emit(false)
        }
    }

}

class AuthViewModelFactory(val repository: ZabbixAPIRepositoryImp) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = AuthViewModel(repository) as T
}