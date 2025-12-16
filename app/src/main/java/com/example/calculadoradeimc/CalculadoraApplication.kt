package com.example.calculadoradeimc


import android.app.Application
import com.example.calculadoradeimc.data.local.AppDatabase
import com.example.calculadoradeimc.data.repository.ImcRepositoryImpl

class CalculadoraApplication : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { ImcRepositoryImpl(database.imcDao()) }
}