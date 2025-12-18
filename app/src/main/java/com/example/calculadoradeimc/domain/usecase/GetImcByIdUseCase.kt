package com.example.calculadoradeimc.domain.usecase

import com.example.calculadoradeimc.domain.model.ImcRecord
import com.example.calculadoradeimc.domain.repository.ImcRepository
import kotlinx.coroutines.flow.Flow

class GetImcByIdUseCase(private val repository: ImcRepository) {
    operator fun invoke(id: Int): Flow<ImcRecord> = repository.getImcById(id)
}