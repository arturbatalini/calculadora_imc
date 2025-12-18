package com.example.calculadoradeimc.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.calculadoradeimc.domain.model.ImcRecord
import kotlinx.coroutines.flow.Flow

@Dao
interface ImcDao {

    @Query("SELECT * FROM imc_history ORDER BY date DESC")
    fun getAll(): Flow<List<ImcRecord>>

    @Query("SELECT * FROM imc_history WHERE id = :id")
    fun getById(id: Int): Flow<ImcRecord>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ImcRecord)

    @Delete
    suspend fun delete(entity: ImcRecord)
}