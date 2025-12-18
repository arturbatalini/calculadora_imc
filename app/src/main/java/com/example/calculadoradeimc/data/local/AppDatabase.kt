package com.example.calculadoradeimc.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.calculadoradeimc.domain.model.ImcRecord

/** GEMINI - início
 * Prompt: Como fazer a implementação do banco de dados (room) utilizando como base a MVVM.
 *
 */

@Database(entities = [ImcRecord::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun imcDao(): ImcDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "imc_database"
                ).build().also { INSTANCE = it }
            }
        }
    }
}

/** GEMINI - final */