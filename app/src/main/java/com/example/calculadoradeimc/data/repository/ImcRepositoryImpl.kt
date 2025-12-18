package com.example.calculadoradeimc.data.repository

import com.example.calculadoradeimc.data.local.ImcDao
import com.example.calculadoradeimc.domain.model.ImcRecord
import com.example.calculadoradeimc.domain.repository.ImcRepository
import kotlinx.coroutines.flow.Flow

class ImcRepositoryImpl(private val dao: ImcDao) : ImcRepository {

    override fun getAllRecords(): Flow<List<ImcRecord>> {
        return dao.getAll()
    }

    override fun getImcById(id: Int): Flow<ImcRecord> {
        return dao.getById(id)
    }

    override suspend fun insertRecord(record: ImcRecord) {
        dao.insert(record)
    }

    override suspend fun deleteRecord(record: ImcRecord) {
        dao.delete(record)
    }
}