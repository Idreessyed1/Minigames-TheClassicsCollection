package com.example.minigameapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.minigameapplication.databinding.ActivityHangmanBinding
import com.example.minigameapplication.databinding.ActivityMainBinding
import java.util.*

import kotlin.collections.ArrayList

class HangmanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHangmanBinding

    private val wordBankMedium = ArrayList<String>()
    private lateinit var guessLetters: Array<String>

    private var guessWord: String = ""
    private var blankWord: String = ""
    private var score: Int = 0
    private var lives: Int = 6
    private var level: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHangmanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeWords()
        initializeGame()
    }

    private fun initializeGame(){

        val pos: Int = Random().nextInt(wordBankMedium.size-1)
        val tempWord: String = wordBankMedium[pos]
        guessLetters = tempWord.split(" ").toTypedArray()


        guessWord = tempWord.toUpperCase().chunked(1).joinToString(separator = " ")
        blankWord = guessWord.replace("[a-zA-Z]".toRegex(), "_")


        binding.guessText.text = guessWord
        binding.livesText.text = "${guessLetters.size}"

    }

    private fun updateGame(){
        if(lives == 0){
            endGame()
        }
        else if (guessLetters.isEmpty()){
            endGame()
        }
        else {
            binding.guessText.text = blankWord
            binding.livesText.text = "$lives"
            binding.scoreText.text = "$score"
        }
    }

    private fun endGame(){
        Toast.makeText(this, "END GAME", Toast.LENGTH_SHORT).show()

    }

    private fun updateWord(letter: String){
        for (i in 0 until guessLetters.size){
            if (guessLetters[i].equals(letter, true)){
                blankWord = blankWord.substring(0, i*2) + letter + blankWord.substring(i*2 + 1)
            }
        }
    }

    fun letterClicked(view: View){
        val alphaBtn = view as Button
        alphaBtn.isClickable = false
        val letter = alphaBtn.text as String
        checkLetter(letter)
    }

    private fun checkLetter(letter: String){
        if (guessLetters.contains(letter)){
            updateWord(letter)
        }
        else{
            lives--
        }
        updateGame()
    }

    fun initializeWords(){
        wordBankMedium.addAll(listOf("impress","reception","salmon","cotton","random","visible",
            "detective","colleague","bitter","contact","monarch","production","number","include",
            "timber","address","compact","triangle","produce","patent","motivation","crevice",
            "survival","transaction","conservative","exercise","farewell","redeem","replacement",
            "reproduce","bridge","assault","parking","assumption","photography","enthusiasm",
            "hostage","protect","financial","display","precedent","miserable","reason","vegetarian",
            "script","complain","missile","sandwich","extend","brainstorm"))
    }
}