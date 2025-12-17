package com.example.calculadoradeimc.domain.usecase

import com.example.calculadoradeimc.domain.model.ImcRecord
import com.example.calculadoradeimc.domain.repository.ImcRepository
import kotlinx.coroutines.flow.Flow

class GetHistoryUseCase(private val repository: ImcRepository) {
    operator fun invoke(): Flow<List<ImcRecord>> = repository.getAllRecords()
}