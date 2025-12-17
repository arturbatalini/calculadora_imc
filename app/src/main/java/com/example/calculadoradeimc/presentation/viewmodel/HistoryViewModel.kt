package com.example.calculadoradeimc.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculadoradeimc.data.repository.ImcRepositoryImpl
import com.example.calculadoradeimc.domain.model.ImcRecord
import com.example.calculadoradeimc.domain.usecase.GetHistoryUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class HistoryViewModel(private val getHistoryUseCase: GetHistoryUseCase) : ViewModel() {
    val historyState: StateFlow<List<ImcRecord>> = getHistoryUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )
}