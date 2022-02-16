package com.example.pennydropnew.types

import com.example.pennydropnew.game.AI

data class Player(
    val playerName: String = "",
    val isHuman: Boolean = true,
    val selectedAI: AI? = null
) {
    var pennies: Int = defaultPennyCount // we’re going to keep pennies and isRolling outside of the constructor since neither is likely to be set to something other than its default value upon initialization
    fun addPennies(count: Int = 1) {
        pennies += count
    }
    var isRolling: Boolean = false
    companion object {
        const val defaultPennyCount = 10 //like we would use a static read-only or constant value in Java
    }
}