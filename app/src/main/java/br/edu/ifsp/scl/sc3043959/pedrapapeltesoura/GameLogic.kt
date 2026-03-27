package br.edu.ifsp.scl.sc3043959.pedrapapeltesoura

import kotlin.random.Random

fun playRound(userMove: Move, playersCount: Int, random: Random = Random.Default): RoundOutcome {
    val aiMoves = List(playersCount - 1) { Move.entries.random(random) }
    val allMoves = listOf(userMove) + aiMoves
    val result = calculateResult(allMoves)
    return RoundOutcome(allMoves = allMoves, result = result)
}

fun formatMoves(allMoves: List<Move>): String {
    val builder = StringBuilder()
    builder.append("Jogador 1 (você): ${allMoves[0].label}")
    for (index in 1 until allMoves.size) {
        builder.append("\nJogador ${index + 1} (CPU): ${allMoves[index].label}")
    }
    return builder.toString()
}

fun calculateResult(allMoves: List<Move>): RoundResult {
    val userMove = allMoves.first()

    if (allMoves.size == 2) {
        val opponentMove = allMoves[1]
        if (userMove == opponentMove) return RoundResult.DRAW
        return if (beats(userMove, opponentMove)) RoundResult.WIN else RoundResult.LOSE
    }

    val distinctMoves = allMoves.toSet()
    if (distinctMoves.size == 1 || distinctMoves.size == 3) return RoundResult.DRAW

    val moveA = distinctMoves.elementAt(0)
    val moveB = distinctMoves.elementAt(1)
    val winningMove = if (beats(moveA, moveB)) moveA else moveB

    return if (userMove == winningMove) RoundResult.WIN else RoundResult.LOSE
}

fun beats(first: Move, second: Move): Boolean {
    return (first == Move.ROCK && second == Move.SCISSORS) ||
        (first == Move.PAPER && second == Move.ROCK) ||
        (first == Move.SCISSORS && second == Move.PAPER)
}

