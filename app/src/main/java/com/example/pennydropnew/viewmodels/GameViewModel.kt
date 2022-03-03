package com.example.pennydropnew.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pennydropnew.game.GameHandler
import com.example.pennydropnew.game.TurnEnd
import com.example.pennydropnew.game.TurnResult
import com.example.pennydropnew.types.Player
import com.example.pennydropnew.types.Slot
import com.example.pennydropnew.types.clear

class GameViewModel : ViewModel() {
    private var players: List<Player> = emptyList()

    val slots =
        MutableLiveData(
            (1..6).map { slotNum -> Slot(slotNum, slotNum != 6) }
        )
    val currentPlayer = MutableLiveData<Player?>()
    val canRoll = MutableLiveData(false)
    val canPass = MutableLiveData(false)
    val currentTurnText = MutableLiveData("")
    val currentStandingsText = MutableLiveData("")

    private var clearText = false
    /*private val repository: PennyDropRepository

    init {
        val database =
            PennyDropDatabase.getDatabase(application, viewModelScope)
    }
    this.repository =
    PennyDropRepository.getInstance(database.pennyDropDao())*/


    /*fun startGame(playersForNewGame: List<Player>) { //brings in the List<Player> and gets a game set up
        this.players = playersForNewGame
        this.currentPlayer.value =
        this.players.firstOrNull().apply {
            this?.isRolling = true
        }
        this.canRoll.value = true
        }*/

    fun roll() {
        slots.value?.let { currentSlots ->
            // Comparing against true saves us a null check
            val currentPlayer = players.firstOrNull { it.isRolling }
            if (currentPlayer != null && canRoll.value == true) {
                updateFromGameHandler(
                    GameHandler.roll(players, currentPlayer, currentSlots)
                )
            }
        }
    }

    fun pass() {
        val currentPlayer = players.firstOrNull { it.isRolling }
        if (currentPlayer != null && canPass.value == true) {
            updateFromGameHandler(GameHandler.pass(players, currentPlayer))
        }
    }

    fun startGame(playersForNewGame: List<Player>) {
        this.players = playersForNewGame
        this.currentPlayer.value = this.players.firstOrNull().apply {
            this?.isRolling = true
        }
        canRoll.value = true
        canPass.value = false
        slots.value?.clear()
        slots.notifyChange()
        currentTurnText.value = "The game has begun!\n"
        currentStandingsText.value = generateCurrentStandings(this.players)
    }


    private fun <T> MutableLiveData<List<T>>.notifyChange() {
        this.value = this.value
    }

    private fun generateCurrentStandings(
        players: List<Player>,
        headerText: String = "Current Standings:"
    ) =
        players.sortedBy { it.pennies }.joinToString(
            separator = "\n",
            prefix = "$headerText\n"
        ) {
            "\t${it.playerName} - ${it.pennies} pennies"
        }

    private fun updateFromGameHandler(result: TurnResult) {
        if (result.currentPlayer != null) {
            currentPlayer.value?.addPennies(result.coinChangeCount ?: 0)
            currentPlayer.value = result.currentPlayer
            this.players.forEach { player ->
                player.isRolling = result.currentPlayer == player
            }
        }
        if (result.lastRoll != null) {
            slots.value?.let { currentSlots ->
                updateSlots(result, currentSlots, result.lastRoll)
            }
        }
        currentTurnText.value = generateTurnText(result)
        currentStandingsText.value = generateCurrentStandings(this.players)
        canRoll.value = result.canRoll
        canPass.value = result.canPass
        if (!result.isGameOver && result.currentPlayer?.isHuman == false) {
            canRoll.value = false
            canPass.value = false
        }
    }

    private fun updateSlots(
        result: TurnResult,
        currentSlots: List<Slot>,
        lastRoll: Int
    ) {
        if (result.clearSlots) {
            currentSlots.clear()
        }
        currentSlots.firstOrNull { it.lastRolled }?.apply { lastRolled = false }
        currentSlots.getOrNull(lastRoll - 1)?.also { slot ->
            if (!result.clearSlots && slot.canBeFilled) slot.isFilled = true

            slot.lastRolled = true
        }
        slots.notifyChange()
    }

    private fun generateTurnText(result: TurnResult): String {
        if (clearText) currentTurnText.value = ""
        clearText = result.turnEnd != null
        val currentText = currentTurnText.value ?: ""
        val currentPlayerName = result.currentPlayer?.playerName ?: "???"
        return when {
            result.isGameOver -> "Game Over"//Game's over, let's get a summary
            result.turnEnd == TurnEnd.Bust -> "Busted"//Player busted, got some pennies
            result.turnEnd == TurnEnd.Pass -> "Passed"//Player passed.
            result.lastRoll != null -> "Roll"//Roll text
            else -> ""
        }
    }
}