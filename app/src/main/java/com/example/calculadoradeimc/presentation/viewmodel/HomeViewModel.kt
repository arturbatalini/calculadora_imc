package com.example.calculadoradeimc.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculadoradeimc.domain.model.ImcRecord
import com.example.calculadoradeimc.domain.model.ImcResult
import com.example.calculadoradeimc.domain.repository.ImcRepository
import com.example.calculadoradeimc.domain.usecase.CalculateImcUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val calculateImcUseCase: CalculateImcUseCase = CalculateImcUseCase(),
    private val repository: ImcRepository
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
    /** GEMINI - início
     * Prompt: Como fazer a implementação do banco de dados (room) utilizando como base a MVVM.
     *
     */
    private fun saveRecord(result: ImcResult) {
        val state = _uiState.value
        val weightVal = state.weight.replace(",", ".").toDoubleOrNull() ?: 0.0
        val heightVal = state.height.toDoubleOrNull() ?: 0.0
        val ageVal = state.age.toIntOrNull() ?: 0




        viewModelScope.launch {
            val record = ImcRecord(
                date = System.currentTimeMillis(),
                weight = weightVal,
                height = heightVal,
                age = ageVal,
                gender = state.gender,
                activityLevel = state.activityLevel,
                imc = result.imc,
                imcClassification = result.imcClassification,
                tmb = result.tbm,
                dailyCalories = result.dailyCalories,
                idealWeight = result.idealWeight
            )
            repository.insertRecord(record)
        }
    }
    /** GEMINI - final */
    fun onCalculateClick() {
        val state = _uiState.value
        val result = calculateImcUseCase(height = state.height, weight = state.weight, age = state.age, gender = state.gender, activityLevel = state.activityLevel)
        if (!result.isError) {
            saveRecord(result)
        }

        _uiState.update {
            it.copy(
                resultMessage = result.message,
                textFieldError = result.isError
            )
        }
    }
}