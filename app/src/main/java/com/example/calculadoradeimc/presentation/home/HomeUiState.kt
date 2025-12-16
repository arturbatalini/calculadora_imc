package com.example.calculadoradeimc.presentation.home

data class HomeUiState(
    val height: String = "",
    val weight: String = "",
    val resultMessage: String = "",
    val textFieldError: Boolean = false
)