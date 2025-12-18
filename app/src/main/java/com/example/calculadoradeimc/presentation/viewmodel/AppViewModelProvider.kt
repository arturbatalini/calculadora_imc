package com.example.calculadoradeimc.presentation.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.calculadoradeimc.CalculadoraApplication
import com.example.calculadoradeimc.data.repository.ImcRepositoryImpl
import com.example.calculadoradeimc.domain.usecase.CalculateImcUseCase
import com.example.calculadoradeimc.domain.usecase.GetHistoryUseCase
import com.example.calculadoradeimc.domain.usecase.GetImcByIdUseCase


object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            val app = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as CalculadoraApplication)

            HomeViewModel(
                calculateImcUseCase = CalculateImcUseCase(),
                repository = app.repository
            )
        }

        /** GEMINI - início
         * Prompt: Como fazer tela de histórico dos cálculos seguindo padrões MVVM, onde
         * Os hitóricos devem ser ordenados por data mais recente, devem apresentar todos
         * os detalhes dos cálculos realizados.

         */
        initializer {
                val app = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as CalculadoraApplication)
                HistoryViewModel(
                getHistoryUseCase = GetHistoryUseCase(app.repository)
            )
        }

        initializer {
            val app = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as CalculadoraApplication)
            DetailViewModel(
                getImcByIdUseCase = GetImcByIdUseCase(app.repository)
            )
        }
    }
}

/** GEMINI - final */