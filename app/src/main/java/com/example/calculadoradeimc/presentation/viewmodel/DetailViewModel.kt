package com.example.calculadoradeimc.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculadoradeimc.domain.model.ImcRecord
import com.example.calculadoradeimc.domain.usecase.GetImcByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/** GEMINI - início
 * Prompt: Como fazer tela de detalhes com base no histórico, onde,
 * ao clicar em um card de histórico, deverá ser redirecionado para uma tela de detalhes
 * que mostre todos os itens calculados, além de detalhes e interpretações textuais para
 * as medidas.
 */


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

/** GEMINI - final */