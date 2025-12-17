package com.example.calculadoradeimc.domain.usecase

import android.annotation.SuppressLint
import com.example.calculadoradeimc.domain.model.ImcResult

class CalculateImcUseCase {

    @SuppressLint("DefaultLocale")
    operator fun invoke(height: String, weight: String, age: String, gender: String, activityLevel: String): ImcResult {
        if (height.isNotEmpty() || weight.isNotEmpty() || age.isNotEmpty() || gender.isNotEmpty() || activityLevel.isNotEmpty()) {
            val weightFormatted = weight.replace(",", ".").toDoubleOrNull()
            val heightFormatted = height.toDoubleOrNull()
            val ageFormatted = age.toIntOrNull()

            if (weightFormatted != null && heightFormatted != null && ageFormatted != null) {
                val imc = weightFormatted / (heightFormatted / 100 * heightFormatted / 100)
                val imcFormatted = String.format("%.2f", imc)
                val imcClassification = when {
                    imc < 18.5 -> "IMC: $imcFormatted \n Abaixo do peso"
                    imc in 18.5..24.9 -> "Peso normal"
                    imc in 25.0..29.9 -> "Sobrepeso"
                    imc in 30.0..34.9 -> "Obesidade (Grau 1)"
                    imc in 35.0..39.9 -> "Obesidade Severa (Grau 2)"
                    else -> "Obesidade Mórbida (Grau 3)"
                }
                val msgImc = when {
                    imc < 18.5 -> "IMC: $imcFormatted \n Abaixo do peso"
                    imc in 18.5..24.9 -> "IMC: $imcFormatted \n Peso normal"
                    imc in 25.0..29.9 -> "IMC: $imcFormatted \n Sobrepeso"
                    imc in 30.0..34.9 -> "IMC: $imcFormatted \n Obesidade (Grau 1)"
                    imc in 35.0..39.9 -> "IMC: $imcFormatted \n Obesidade Severa (Grau 2)"
                    else -> "IMC: $imcFormatted \n Obesidade Mórbida (Grau 3)"
                }

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
                    idealWeight+50
                } else {
                    idealWeight+45.5
                }
                val msgIdealWeight = "Peso ideal: " + String.format("%.2f", idealWeightGender)


                val activityFactor = when (activityLevel) {
                    "Sedentário" -> 1.2
                    "Leve" -> 1.375
                    "Moderado" -> 1.55
                    "Intenso" -> 1.725
                    else -> 1.725
                }


                val dailyCalories = finalTmb * activityFactor
                val msgCalories = "Calorias Diárias: " + String.format("%.2f", dailyCalories)


                return ImcResult(message = "$msgImc\n$msgTmb\n$msgIdealWeight\n$msgCalories", isError = false, imc = imc, imcClassification = imcClassification, tbm = finalTmb, dailyCalories = dailyCalories, idealWeight = idealWeightGender)
            }
        }
        return ImcResult(message = "Preencha todos os campos", isError = true, imc = 0.0, imcClassification = "", tbm = 0.0, dailyCalories = 0.0, idealWeight = 0.0)
    }
}