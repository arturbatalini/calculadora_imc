package com.example.calculadoradeimc.domain.model


data class ImcResult(
    val message: String,
    val isError: Boolean,
    val imc: Double,
    val imcClassification: String,
    val tbm: Double,
    val dailyCalories: Double,
    val idealWeight: Double
)