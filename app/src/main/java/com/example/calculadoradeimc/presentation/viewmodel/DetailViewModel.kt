package com.example.calculadoradeimc.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculadoradeimc.domain.model.ImcRecord
import com.example.calculadoradeimc.domain.usecase.GetImcByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val getImcByIdUseCase: GetImcByIdUseCase) : ViewModel() {
    private val _uiState = MutableStateFlow<ImcRecord?>(null)
    val uiState = _uiState.asStateFlow()

    fun getRecord(id: Int) {
        viewModelScope.launch {
            getImcByIdUseCase(id).collect { record ->
                _uiState.value = record
            }
        }
    }
}