package com.example.calculadoradeimc.domain.usecase

import com.example.calculadoradeimc.domain.model.ImcRecord
import com.example.calculadoradeimc.domain.repository.ImcRepository
import kotlinx.coroutines.flow.Flow


/** GEMINI - início
 * Prompt: Como fazer tela de histórico dos cálculos seguindo padrões MVVM, onde
 * Os hitóricos devem ser ordenados por data mais recente, devem apresentar todos
 * os detalhes dos cálculos realizados.

 */
class GetHistoryUseCase(private val repository: ImcRepository) {
    operator fun invoke(): Flow<List<ImcRecord>> = repository.getAllRecords()
}
/** GEMINI - final */
