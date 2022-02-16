package com.example.pennydropnew.game

data class AI(val name: String) {
    override fun toString() = name
    companion object {   //object that is associated with a given class
        @JvmStatic  //There’s only one instance of this object per class (meaning it’s a singleton) and functions/values/variables in here can be accessed in a similar way to how we would reference static types
        val basicAI = listOf(   // we can get basicAI elsewhere by referencing AI.basicAI
            AI("TwoFace"),
            AI("No Go Noah"),
            AI("Bail Out Beulah"),
            AI("Fearful Fred"),
            AI("Even Steven"),
            AI("Riverboat Ron"),
            AI("Sammy Sixes"),
            AI("Random Rachael")
        )
    }
}