package com.example.calculadoradeimc.domain.repository

import com.example.calculadoradeimc.domain.model.ImcRecord
import kotlinx.coroutines.flow.Flow

interface ImcRepository {
    fun getAllRecords(): Flow<List<ImcRecord>>
    fun getImcById(id: Int): Flow<ImcRecord>
    suspend fun insertRecord(record: ImcRecord)
    suspend fun deleteRecord(record: ImcRecord)
}