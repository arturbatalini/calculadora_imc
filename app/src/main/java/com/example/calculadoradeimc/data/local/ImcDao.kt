package com.example.calculadoradeimc.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ImcDao {

    @Query("SELECT * FROM imc_history ORDER BY date DESC")
    fun getAll(): Flow<List<ImcEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ImcEntity)

    @Delete
    suspend fun delete(entity: ImcEntity)
}