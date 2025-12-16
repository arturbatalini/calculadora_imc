package com.example.calculadoradeimc.presentation.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.calculadoradeimc.CalculadoraApplication
import com.example.calculadoradeimc.domain.usecase.CalculateImcUseCase

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            val app = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as CalculadoraApplication)

            HomeViewModel(
                calculateImcUseCase = CalculateImcUseCase(),
                repository = app.repository
            )
        }
    }
}