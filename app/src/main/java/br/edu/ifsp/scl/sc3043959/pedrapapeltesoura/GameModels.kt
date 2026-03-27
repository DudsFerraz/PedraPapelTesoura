package br.edu.ifsp.scl.sc3043959.pedrapapeltesoura

enum class Move(val label: String) {
    ROCK("Pedra"),
    PAPER("Papel"),
    SCISSORS("Tesoura")
}

enum class RoundResult {
    WIN,
    LOSE,
    DRAW
}

data class RoundOutcome(
    val allMoves: List<Move>,
    val result: RoundResult
)

