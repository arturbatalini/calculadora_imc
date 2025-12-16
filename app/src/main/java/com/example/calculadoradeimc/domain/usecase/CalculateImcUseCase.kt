package com.example.calculadoradeimc.domain.usecase

import android.annotation.SuppressLint
import com.example.calculadoradeimc.domain.model.ImcResult

class CalculateImcUseCase {

    @SuppressLint("DefaultLocale")
    operator fun invoke(height: String, weight: String): ImcResult {
        if (height.isNotEmpty() && weight.isNotEmpty()) {
            val weightFormatted = weight.replace(",", ".").toDoubleOrNull()
            val heightFormatted = height.toDoubleOrNull()

            if (weightFormatted != null && heightFormatted != null) {
                val imc = weightFormatted / (heightFormatted / 100 * heightFormatted / 100)
                val imcFormatted = String.format("%.2f", imc)

                val msg = when {
                    imc < 18.5 -> "IMC: $imcFormatted \n Abaixo do peso"
                    imc in 18.5..24.9 -> "IMC: $imcFormatted \n Peso normal"
                    imc in 25.0..29.9 -> "IMC: $imcFormatted \n Sobrepeso"
                    imc in 30.0..34.9 -> "IMC: $imcFormatted \n Obesidade (Grau 1)"
                    imc in 35.0..39.9 -> "IMC: $imcFormatted \n Obesidade Severa (Grau 2)"
                    else -> "IMC: $imcFormatted \n Obesidade Mórbida (Grau 3)"
                }

                return ImcResult(message = msg, isError = false)
            }
        }
        return ImcResult(message = "Preencha todos os campos", isError = true)
    }
}