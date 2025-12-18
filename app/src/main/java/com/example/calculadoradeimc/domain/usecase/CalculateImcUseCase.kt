package com.example.calculadoradeimc.domain.usecase

import android.annotation.SuppressLint
import com.example.calculadoradeimc.domain.model.ImcResult

class CalculateImcUseCase {

    @SuppressLint("DefaultLocale")
    operator fun invoke(height: String, weight: String, age: String, gender: String, activityLevel: String): ImcResult {

        if (height.isBlank()) return createError("O campo Altura é obrigatório.")
        if (weight.isBlank()) return createError("O campo Peso é obrigatório.")
        if (age.isBlank()) return createError("O campo Idade é obrigatório.")
        if (gender.isBlank()) return createError("Por favor, selecione o Gênero.")
        if (activityLevel.isBlank()) return createError("Por favor, selecione o Nível de Atividade.")

        val weightFormatted = weight.replace(",", ".").toDoubleOrNull()
        val heightFormatted = height.replace(",", ".").toDoubleOrNull()
        val ageFormatted = age.toIntOrNull()
1
        if (weightFormatted == null) return createError("Peso inválido. Digite apenas números.")
        if (heightFormatted == null) return createError("Altura inválida. Digite apenas números.")
        if (ageFormatted == null) return createError("Idade inválida. Digite apenas números inteiros.")

        if (weightFormatted <= 0 || weightFormatted > 600) {
            return createError("Peso inválido. Insira um valor positivo e realista, em kg (ex: 70.50).")
        }

        if (heightFormatted <= 0 || heightFormatted > 300) {
            return createError("Altura inválida. Insira um valor positivo e realista, em cm (ex: 175).")
        }

        if (ageFormatted <= 0 || ageFormatted > 120) {
            return createError("Idade inválida. Insira uma idade entre 1 e 120 anos.")
        }

        val imc = weightFormatted / (heightFormatted / 100 * heightFormatted / 100)
        val imcFormatted = String.format("%.2f", imc)

        val imcClassification = when {
            imc < 18.5 -> "Abaixo do peso"
            imc in 18.5..24.9 -> "Peso normal"
            imc in 25.0..29.9 -> "Sobrepeso"
            imc in 30.0..34.9 -> "Obesidade (Grau 1)"
            imc in 35.0..39.9 -> "Obesidade Severa (Grau 2)"
            else -> "Obesidade Mórbida (Grau 3)"
        }

        val msgImc = "IMC: $imcFormatted \n $imcClassification"

        val tmb = (10 * weightFormatted) + (6.25 * heightFormatted) - (5 * ageFormatted)
        val finalTmb = if (gender == "Masculino"){
            tmb + 5
        } else {
            tmb - 161
        }
        val msgTmb = "TMB: " + String.format("%.2f", finalTmb)

        val heightInches = heightFormatted / 2.54
        val idealWeight = 2.3 * (heightInches - 60)
        val idealWeightGender = if (gender == "Masculino"){
            idealWeight + 50
        } else {
            idealWeight + 45.5
        }

        val finalIdealWeight = if (idealWeightGender > 0) idealWeightGender else 0.0
        val msgIdealWeight = "Peso ideal: " + String.format("%.2f", finalIdealWeight)

        val activityFactor = when (activityLevel) {
            "Sedentário" -> 1.2
            "Leve" -> 1.375
            "Moderado" -> 1.55
            "Intenso" -> 1.725
            else -> 1.725
        }

        val dailyCalories = finalTmb * activityFactor
        val msgCalories = "Calorias Diárias: " + String.format("%.2f", dailyCalories)

        return ImcResult(
            message = "$msgImc\n$msgTmb\n$msgIdealWeight\n$msgCalories",
            isError = false,
            imc = imc,
            imcClassification = imcClassification,
            tbm = finalTmb,
            dailyCalories = dailyCalories,
            idealWeight = finalIdealWeight
        )
    }

    private fun createError(msg: String): ImcResult {
        return ImcResult(
            message = msg,
            isError = true,
            imc = 0.0,
            imcClassification = "",
            tbm = 0.0,
            dailyCalories = 0.0,
            idealWeight = 0.0
        )
    }
}