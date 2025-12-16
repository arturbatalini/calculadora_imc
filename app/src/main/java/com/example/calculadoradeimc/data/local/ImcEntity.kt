package com.example.calculadoradeimc.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.calculadoradeimc.domain.model.ImcRecord

@Entity(tableName = "imc_history")
data class ImcEntity(
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


fun ImcEntity.toDomain() = ImcRecord(
    id = id, date = date, weight = weight, height = height, age = age,
    gender = gender, activityLevel = activityLevel, imc = imc,
    imcClassification = imcClassification, tmb = tmb,
    dailyCalories = dailyCalories, idealWeight = idealWeight
)

fun ImcRecord.toEntity() = ImcEntity(
    id = id, date = date, weight = weight, height = height, age = age,
    gender = gender, activityLevel = activityLevel, imc = imc,
    imcClassification = imcClassification, tmb = tmb,
    dailyCalories = dailyCalories, idealWeight = idealWeight
)