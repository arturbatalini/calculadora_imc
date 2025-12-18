package com.example.calculadoradeimc.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculadoradeimc.presentation.viewmodel.AppViewModelProvider
import com.example.calculadoradeimc.presentation.viewmodel.DetailViewModel
import com.example.calculadoradeimc.ui.theme.Blue
import com.example.calculadoradeimc.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailScreen(
    recordId: Int,
    viewModel: DetailViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onBack: () -> Unit
) {
    LaunchedEffect(recordId) {
        viewModel.getRecord(recordId)
    }

    val record by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Análise Detalhada") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Blue,
                    titleContentColor = White,
                    navigationIconContentColor = White,
                    actionIconContentColor = White
                )
            )
        }
    ) { paddingValues ->
        if (record != null) {
            val imcColor = getImcColorDetail(record!!.imc)

            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(Color(0xFFF5F5F5))
                    .padding(16.dp)
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text("Seu IMC é", style = MaterialTheme.typography.titleMedium, color = Color.Gray)
                Text(
                    text = String.format("%.2f", record!!.imc),
                    style = MaterialTheme.typography.displayLarge,
                    fontWeight = FontWeight.Bold,
                    color = imcColor
                )
                ContainerClassificacao(record!!.imcClassification, imcColor)

                Spacer(modifier = Modifier.height(24.dp))

                DetailCard(title = "O que isso significa?", icon = Icons.Default.Info) {
                    Text(
                        text = getImcAdvice(record!!.imc),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.DarkGray,
                        lineHeight = 20.sp
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                DetailCard(title = "Dados Registrados", icon = null) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        DataPoint("Peso", "${record!!.weight} kg")
                        DataPoint("Altura", "${record!!.height} cm")
                        DataPoint("Idade", "${record!!.age} anos")
                        DataPoint("Sexo", record!!.gender)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                DetailCard(title = "Metabolismo e Energia", icon = null) {
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Taxa Metabólica Basal (TMB)", fontWeight = FontWeight.Bold)
                            Text("${record!!.tmb.toInt()} kcal", color = Color(0xFF1565C0), fontWeight = FontWeight.Bold)
                        }
                        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Gasto Calórico Diário", fontWeight = FontWeight.Bold)
                            Text("${record!!.dailyCalories.toInt()} kcal", color = Color(0xFF2E7D32), fontWeight = FontWeight.Bold)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (record!!.idealWeight > 0) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD))
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("Peso Ideal Estimado", style = MaterialTheme.typography.labelLarge, color = Color(0xFF1565C0))
                            Text(
                                text = "${String.format("%.1f", record!!.idealWeight)} kg",
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1565C0)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
            }
        } else {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Carregando...")
            }
        }
    }
}

@Composable
private fun DetailCard(title: String, icon: androidx.compose.ui.graphics.vector.ImageVector?, content: @Composable () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (icon != null) {
                    Icon(icon, contentDescription = null, tint = Color(0xFF1565C0), modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.size(8.dp))
                }
                Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = Color.Black)
            }
            Spacer(modifier = Modifier.height(12.dp))
            content()
        }
    }
}

@Composable
private fun DataPoint(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Text(label, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
    }
}

@Composable
private fun ContainerClassificacao(text: String, color: Color) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(color.copy(alpha = 0.2f))
            .padding(horizontal = 16.dp, vertical = 6.dp)
    ) {
        Text(text = text, color = color, fontWeight = FontWeight.Bold)
    }
}

private fun getImcColorDetail(imc: Double): Color {
    return when {
        imc < 18.5 -> Color(0xFFFFA000)
        imc < 24.9 -> Color(0xFF388E3C)
        imc < 29.9 -> Color(0xFFFBC02D)
        imc < 34.9 -> Color(0xFFE64A19)
        else -> Color(0xFFD32F2F)
    }
}

private fun getImcAdvice(imc: Double): String {
    return when {
        imc < 18.5 -> "Seu IMC indica que você está abaixo do peso ideal. É importante focar em uma dieta rica em nutrientes para ganho de massa saudável."
        imc < 24.9 -> "Parabéns! Seu IMC está na faixa normal. Mantenha uma dieta equilibrada e exercícios regulares para continuar assim."
        imc < 29.9 -> "Você está na faixa de sobrepeso. Pequenos ajustes na alimentação e caminhadas diárias podem ajudar a voltar ao peso ideal."
        imc < 34.9 -> "Obesidade Grau I. É recomendável atenção. Evite ultraprocessados e tente incluir atividades físicas leves na rotina."
        imc < 39.9 -> "Obesidade Grau II. O risco para a saúde aumenta. Procure ajuda profissional para um plano de emagrecimento seguro."
        else -> "Obesidade Grau III. É fundamental buscar acompanhamento médico para gerenciar o peso e proteger sua saúde."
    }
}