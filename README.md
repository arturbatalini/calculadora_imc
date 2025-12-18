# Trabalho 2 - Calculadora de Saúde & IMC

Repositório oficial para o Trabalho 2 da disciplina de Programação para Dispositivos Móveis.

## Integrantes do Grupo:
* Artur Batalini Coelho Alvarim - 12211GIN003
* Luiz Alexandre Anchieta Freitas - 12211GIN008

## Objetivo
Desenvolver e refatorar uma aplicação Android nativa utilizando Kotlin e Jetpack Compose. O projeto consiste em uma calculadora de indicadores de saúde avançada, focada não apenas no cálculo, mas na persistência de dados, validação de segurança e interpretação detalhada dos indicadores de saúde.

## Funcionalidades Principais

### 1. Calculadora de índices (IMC, TMB, PESO IDEAL, NECESSIDADE CALÓRICA DIÁRIA)
O aplicativo implementa regras de negócio para garantir a integridade dos dados antes do processamento:
* Bloqueio de Valores Negativos: Impede a entrada de peso ou altura menores que zero.
* Verificação de Altura Irreal: O sistema detecta e alerta o usuário caso a altura inserida seja biologicamente improvável (ex: valores irreais de altura e peso, campos negativos, campos não preenchidos)

### 2. Histórico Completo (Persistência)
* Armazenamento local de todas as medições realizadas pelo usuário.
* Visualização em lista cronológica descendente, permitindo o acompanhamento da evolução ao longo do tempo.

### 3. Detalhamento e Interpretação Avançada
Ao clicar em um item do histórico, o usuário navega para uma tela de detalhes que oferece:
* Dados da Medição: Exibição do Peso e Altura originais.
* Resultado do IMC: O valor calculado.
* Classificação Textual: Interpretação automática baseada na tabela da OMS (ex: "Peso Ideal", "Sobrepeso", "Obesidade Grau I").
* Contexto: Explicação visual sobre o que a classificação representa.

##  Estrutura de Telas e Navegação
O projeto utiliza o Navigation Compose para gerenciar o fluxo entre três telas principais:
1.  Home Screen: Tela de entrada para inserção de dados e botão de cálculo. Acesso rápido ao histórico.
2.  History Screen: Lista contendo os registros salvos. Cada card é clicável.
3.  Details Screen: Tela dinâmica que recebe o ID ou objeto da medição e renderiza a análise completa e a interpretação textual do resultado.

## Tecnologias e Decisões de Arquitetura
* Linguagem: Kotlin.
* Interface (UI): Jetpack Compose (Material Design 3).
* Arquitetura: MVVM (Model-View-ViewModel) para separação de responsabilidades.
* Persistência de Dados: Room Database (SQLite) para salvar o histórico de forma eficiente.
* Navegação: Jetpack Navigation Compose para trânsito de dados entre telas.
