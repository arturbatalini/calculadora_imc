package com.example.calculadoradeimc.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculadoradeimc.presentation.viewmodel.HomeViewModel
import com.example.calculadoradeimc.ui.theme.Blue
import com.example.calculadoradeimc.ui.theme.White

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(vm: HomeViewModel = viewModel()) {

    val state by vm.uiState.collectAsState()
    /** GEMINI - início
     * Prompt: Para variável gender,
     * transformá-la em um select type de lista suspensa, para
     * poder selecionar apenas entre masculino e feminino
     */
    var expanded by remember { mutableStateOf(false) }
    val genderOptions = listOf("Masculino", "Feminino")
    /** GEMINI - final */

    var expandedActivity by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Calculadora de IMC") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Blue,
                    titleContentColor = White
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(color = White)
                .verticalScroll(rememberScrollState()),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Altura (cm)",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(20.dp, 100.dp, 0.dp, 0.dp)
                )

                Text(
                    text = "Peso (kg)",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(0.dp, 100.dp, 20.dp, 0.dp)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedTextField(
                    value = state.height,
                    onValueChange = {
                        if (it.length <= 3) vm.onHeightChange(it)
                    },
                    label = { Text(text = "Ex: 165") },
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(20.dp, 0.dp, 0.dp, 20.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.NumberPassword
                    ),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = White,
                        focusedContainerColor = White,
                        errorContainerColor = White,
                        focusedLabelColor = Blue,
                        focusedIndicatorColor = Blue,
                        cursorColor = Blue,
                    ),
                    isError = state.textFieldError
                )

                OutlinedTextField(
                    value = state.weight,
                    onValueChange = {
                        if (it.length <= 7) vm.onWeightChange(it)
                    },
                    label = { Text(text = "Ex: 70.50") },
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(20.dp, 0.dp, 20.dp, 20.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal
                    ),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = White,
                        focusedContainerColor = White,
                        errorContainerColor = White,
                        focusedLabelColor = Blue,
                        focusedIndicatorColor = Blue,
                        cursorColor = Blue,
                    ),
                    isError = state.textFieldError
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Idade",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(20.dp, 0.dp, 0.dp, 0.dp)
                        .fillMaxWidth(0.5f)
                )

                Text(
                    text = "Sexo",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(0.dp, 0.dp, 20.dp, 0.dp)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedTextField(
                    value = state.age,
                    onValueChange = {
                        if (it.length <= 3) vm.onAgeChange(it)
                    },
                    label = { Text(text = "Ex: 18") },
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(20.dp, 0.dp, 0.dp, 20.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.NumberPassword
                    ),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = White,
                        focusedContainerColor = White,
                        errorContainerColor = White,
                        focusedLabelColor = Blue,
                        focusedIndicatorColor = Blue,
                        cursorColor = Blue,
                    ),
                    isError = state.textFieldError
                )
                /** GEMINI - início
                 * Prompt: Para variável gender,
                 * transformá-la em um select type de lista suspensa, para
                 * poder selecionar apenas entre masculino e feminino
                 */
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded },
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .weight(1f)
                        .padding(20.dp, 0.dp, 20.dp, 20.dp)
                ) {
                    OutlinedTextField(
                        value = state.gender,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text(text = "Selecione") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = White,
                            focusedContainerColor = White,
                            errorContainerColor = White,
                            focusedLabelColor = Blue,
                            focusedIndicatorColor = Blue,
                            cursorColor = Blue,
                        ),
                        isError = state.textFieldError,
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()

                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier.background(White)
                    ) {
                        genderOptions.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(text = option, color = Blue) },
                                onClick = {
                                    vm.onGenderChange(option)
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }
            /** GEMINI - final */

            /** GEMINI - início
             * Prompt: Faça o mesmo agora para a
             * seleção de nível de atividade física
             * (Sedentário, Leve, Moderado, Intenso).
             *
             */

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Fator de Atividade Física",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(20.dp, 20.dp, 0.dp, 0.dp)
                )
            }
            
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 0.dp, 20.dp, 20.dp)
            ) {
                ExposedDropdownMenuBox(
                    expanded = expandedActivity,
                    onExpandedChange = { expandedActivity = !expandedActivity },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = state.activityLevel,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text(text = "Selecione o nível") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedActivity) },
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = White,
                            focusedContainerColor = White,
                            errorContainerColor = White,
                            focusedLabelColor = Blue,
                            focusedIndicatorColor = Blue,
                            cursorColor = Blue,
                        ),
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth(),
                        isError = state.textFieldError
                    )

                    ExposedDropdownMenu(
                        expanded = expandedActivity,
                        onDismissRequest = { expandedActivity = false },
                        modifier = Modifier.background(White)
                    ) {
                        vm.activityOptions.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(text = option, color = Blue) },
                                onClick = {
                                    vm.onActivityLevelChange(option)
                                    expandedActivity = false
                                }
                            )
                        }
                    }
                }
            }
            /** GEMINI - final */

            Button(
                onClick = { vm.onCalculateClick() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Blue,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
                    .padding(start = 50.dp, top = 20.dp, end = 50.dp, bottom = 20.dp)
            ) {
                Text(
                    text = "CALCULAR",
                    fontSize = 18.sp,
                    color = White,
                    fontWeight = FontWeight.Bold,
                )
            }

            Text(
                text = state.resultMessage,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Blue,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )
        }
    }
}



