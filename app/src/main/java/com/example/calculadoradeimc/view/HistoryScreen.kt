package com.example.calculadoradeimc.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculadoradeimc.domain.model.ImcRecord
import com.example.calculadoradeimc.presentation.viewmodel.AppViewModelProvider
import com.example.calculadoradeimc.presentation.viewmodel.HistoryViewModel
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

private val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm", Locale("pt", "BR"))

@Composable
fun HistoryScreen(
    viewModel: HistoryViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onBack: () -> Unit
) {
    val historyList by viewModel.historyState.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onBack) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
            }
            Text("Histórico de IMC", style = MaterialTheme.typography.titleLarge)
        }

        LazyColumn {
            items(historyList) { record ->
                HistoryItem(record)
            }
        }
    }
}

@Composable
fun HistoryItem(record: ImcRecord) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            val instant = Instant.ofEpochMilli(record.date)
            val dataReal = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())

            Text(
                text = dataReal.format(dateFormatter),
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )

            Text(
                text = "IMC: ${String.format("%.2f", record.imc)}",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}