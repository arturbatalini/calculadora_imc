package com.example.calculadoradeimc.data.repository

import com.example.calculadoradeimc.data.local.ImcDao
import com.example.calculadoradeimc.data.local.toDomain
import com.example.calculadoradeimc.data.local.toEntity
import com.example.calculadoradeimc.domain.model.ImcRecord
import com.example.calculadoradeimc.domain.repository.ImcRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ImcRepositoryImpl(private val dao: ImcDao) : ImcRepository {
    override fun getAllRecords(): Flow<List<ImcRecord>> {
        return dao.getAll().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun insertRecord(record: ImcRecord) {
        dao.insert(record.toEntity())
    }

    override suspend fun deleteRecord(record: ImcRecord) {
        dao.delete(record.toEntity())
    }

    class GetHistoryUseCase(private val repository: ImcRepository) {
        operator fun invoke(): Flow<List<ImcRecord>> = repository.getAllRecords()
    }

}