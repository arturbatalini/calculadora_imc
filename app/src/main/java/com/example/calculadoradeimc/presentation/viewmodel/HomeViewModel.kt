package com.example.calculadoradeimc.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.calculadoradeimc.domain.usecase.CalculateImcUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel(
    private val calculateImcUseCase: CalculateImcUseCase = CalculateImcUseCase()
) : ViewModel() {

    val genderOptions = listOf("Masculino", "Feminino")

    val activityOptions = listOf("Sedentário", "Leve", "Moderado", "Intenso")

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    fun onHeightChange(value: String) {
        _uiState.update { it.copy(height = value) }
    }

    fun onWeightChange(value: String) {
        _uiState.update { it.copy(weight = value) }
    }

    fun onAgeChange(value: String) {
        _uiState.update { it.copy(age = value) }
    }

    /** GEMINI - início
     * Prompt: Para variável gender, transformá-la em um select type de lista suspensa, para
     * poder selecionar apenas entre masculino e feminino
     *
     */
    fun onGenderChange(selectedGender: String) {
        _uiState.update { currentState ->
            currentState.copy(gender = selectedGender)
        }
    }
    /** GEMINI - final */
    fun onActivityLevelChange(newLevel: String) {
        _uiState.update { currentState ->
            currentState.copy(activityLevel = newLevel)
        }
    }

    fun onCalculateClick() {
        val state = _uiState.value
        val result = calculateImcUseCase(height = state.height, weight = state.weight, age = state.age, gender = state.gender, activityLevel = state.activityLevel)

        _uiState.update {
            it.copy(
                resultMessage = result.message,
                textFieldError = result.isError
            )
        }
    }
}