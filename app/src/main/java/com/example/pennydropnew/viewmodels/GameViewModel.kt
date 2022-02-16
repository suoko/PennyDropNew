package com.example.pennydropnew.viewmodels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pennydropnew.types.Player
import com.example.pennydropnew.types.Slot

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

    fun startGame(playersForNewGame: List<Player>) { //brings in the List<Player> and gets a game set up
        this.players = playersForNewGame
        this.currentPlayer.value =
        this.players.firstOrNull().apply {
            this?.isRolling = true
        }
        this.canRoll.value = true
        }
    fun roll() {
        // Implementing later
    }
    fun pass() {
        // Implementing later
    }
}