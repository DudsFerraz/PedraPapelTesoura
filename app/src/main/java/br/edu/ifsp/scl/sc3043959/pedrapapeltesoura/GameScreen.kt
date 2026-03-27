package br.edu.ifsp.scl.sc3043959.pedrapapeltesoura

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.edu.ifsp.scl.sc3043959.pedrapapeltesoura.ui.theme.PedraPapelTesouraTheme

@Composable
fun JoKenPoScreen(lifecycleMessage: String, modifier: Modifier = Modifier) {
    var playersCount by rememberSaveable { mutableIntStateOf(2) }
    var resultText by rememberSaveable { mutableStateOf("Escolha uma jogada para iniciar a rodada.") }
    var playerMovesText by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Pedra, Papel e Tesoura", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Selecione a quantidade de participantes")

        Row(modifier = Modifier.padding(top = 8.dp)) {
            OutlinedButton(onClick = { playersCount = 2 }) {
                Text("2 jogadores")
            }
            Spacer(modifier = Modifier.width(8.dp))
            OutlinedButton(onClick = { playersCount = 3 }) {
                Text("3 jogadores")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Jogadores na rodada: $playersCount")
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Escolha sua jogada")

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Move.entries.forEach { move ->
                Button(
                    onClick = {
                        val outcome = playRound(userMove = move, playersCount = playersCount)
                        playerMovesText = formatMoves(outcome.allMoves)
                        resultText = when (outcome.result) {
                            RoundResult.WIN -> "Resultado: Vitoria"
                            RoundResult.LOSE -> "Resultado: Derrota"
                            RoundResult.DRAW -> "Resultado: Empate"
                        }
                    }
                ) {
                    Text(text = move.label)
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        Text(text = playerMovesText)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = resultText, style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "Ultimo callback do ciclo de vida:")
        Text(text = lifecycleMessage)
    }
}

@Preview(showBackground = true)
@Composable
private fun JoKenPoPreview() {
    PedraPapelTesouraTheme {
        JoKenPoScreen(lifecycleMessage = "onResume: Activity em primeiro plano")
    }
}

