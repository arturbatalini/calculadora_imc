package com.example.calculadoradeimc.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "imc_history")
data class ImcRecord(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: Long,
    val weight: Double,
    val height: Double,
    val age: Int,
    val gender: String,
    val activityLevel: String,
    val imc: Double,
    val imcClassification: String,
    val tmb: Double,
    val dailyCalories: Double,
    val idealWeight: Double
)
