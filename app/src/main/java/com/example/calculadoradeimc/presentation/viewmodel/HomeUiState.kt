package com.example.calculadoradeimc.presentation.viewmodel

data class HomeUiState(
    val height: String = "",
    val weight: String = "",
    val resultMessage: String = "",
    val textFieldError: Boolean = false,
    val age : String = "",
    val gender : String = "",
    val activityLevel: String = ""
)


