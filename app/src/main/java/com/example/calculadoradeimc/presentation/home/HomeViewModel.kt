package com.example.calculadoradeimc.presentation.home

import androidx.lifecycle.ViewModel
import com.example.calculadoradeimc.domain.usecase.CalculateImcUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel(
    private val calculateImcUseCase: CalculateImcUseCase = CalculateImcUseCase()
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    fun onHeightChange(value: String) {
        _uiState.update { it.copy(height = value) }
    }

    fun onWeightChange(value: String) {
        _uiState.update { it.copy(weight = value) }
    }

    fun onCalculateClick() {
        val state = _uiState.value
        val result = calculateImcUseCase(height = state.height, weight = state.weight)

        _uiState.update {
            it.copy(
                resultMessage = result.message,
                textFieldError = result.isError
            )
        }
    }
}