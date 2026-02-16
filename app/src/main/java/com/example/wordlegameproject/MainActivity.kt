package com.example.wordlegameproject

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {

    private var guessCount = 0
    private lateinit var input: EditText
    private lateinit var guessButton: Button
    private lateinit var Guess1: TextView
    private lateinit var Guess2: TextView
    private lateinit var Guess3: TextView
    private lateinit var Guess1c: TextView
    private lateinit var Guess2c: TextView
    private lateinit var Guess3c: TextView

    private lateinit var correctWord: TextView
    private lateinit var wordToGuess: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        input = findViewById(R.id.entry)
        guessButton = findViewById(R.id.button)
        Guess1 = findViewById(R.id.guess1)
        Guess2 = findViewById(R.id.guess2)
        Guess3 = findViewById(R.id.guess3)
        Guess1c = findViewById(R.id.guess1c)
        Guess2c = findViewById(R.id.guess2c)
        Guess3c = findViewById(R.id.guess3c)

        wordToGuess = FourLetterWordList.getRandomFourLetterWord()

        correctWord.text  = ""

        guessButton.setOnClickListener { makeGuess() }

    }
    private fun makeGuess(){
        var guess_input = input.text.toString().uppercase()

        if (guessCount == 0) {
            Guess1.text = guess_input
            Guess1c.text = checkGuess(guess_input)
        }
        if (guessCount == 1) {
            Guess2.text = guess_input
            Guess2c.text = checkGuess(guess_input)
        }
        if (guessCount == 2) {
            Guess3.text = guess_input
            Guess3c.text = checkGuess(guess_input)

            correctWord.text ="Correct word: $wordToGuess"
            guessButton.isEnabled = false
        }
        guessCount++

    }

    private fun checkGuess(guess: String) : String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            } else if (guess[i] in wordToGuess) {
                result += "+"
            } else {
                result += "X"
            }
        }
        return result
    }
}