package com.sample.tictactoewithkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.log

class MainActivity : AppCompatActivity() , View.OnClickListener{

    var time:Long  = 5000
    var player1Score = 0
    var player2Score = 0
    var turn = true
    var rounds = 0
    val board: MutableList<MutableList<Button>> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (i in 0 until 3){
            val row = mutableListOf<Button>()
            for (j in 0 until 3){
                val isString = "button_$i$j"
                val buttonID = resources.getIdentifier(isString,"id", packageName)
                val button = findViewById<Button>(buttonID)
                row.add(button)
                button.setOnClickListener(this)
                button.setBackgroundColor(resources.getColor(R.color.grey))
            }
            board.add(row)
        }

        reset_game.setOnClickListener {
            resetBoard()
            player1Score = 0
            player2Score = 0
            rounds = 0
            turn = true
            player1_score.text = "Player 1: 0"
            player2_score.text = "Player 2: 0"
        }
    }

    override fun onClick(v: View?) {
        if(!(v as Button).text.toString().equals("")){
            return
        }

        if(turn){
            v.text = "X"
        }else{
            v.text = "O"
        }

        rounds++

        if(checkWinner()){
            starttimer()
            if(turn) player1Win()
            else player2Win()
        }else if(rounds == 9){
            draw()
        }else{
            turn = !turn
        }
    }

    private fun checkWinner(): Boolean{
        for(j in 0 until  3){
            if (!board[j][0].text.equals("") &&
                board[j][0].text.toString().equals(board[j][1].text.toString()) &&
                board[j][0].text.toString().equals(board[j][2].text.toString())){

                board[j][0].setBackgroundColor(resources.getColor(R.color.blue))
                board[j][1].setBackgroundColor(resources.getColor(R.color.blue))
                board[j][2].setBackgroundColor(resources.getColor(R.color.blue))

                return true
            }

        }

        for(j in 0 until  3){
            if (!board[0][j].text.equals("") &&
                board[0][j].text.toString().equals(board[1][j].text.toString()) &&
                board[0][j].text.toString().equals(board[2][j].text.toString())){

                board[0][j].setBackgroundColor(resources.getColor(R.color.blue))
                board[1][j].setBackgroundColor(resources.getColor(R.color.blue))
                board[2][j].setBackgroundColor(resources.getColor(R.color.blue))

                return true
            }


        }

        if (!board[0][0].text.equals("") &&
            board[0][0].text.toString().equals(board[1][1].text.toString()) &&
            board[0][0].text.toString().equals(board[2][2].text.toString())){

            board[0][0].setBackgroundColor(resources.getColor(R.color.blue))
            board[1][1].setBackgroundColor(resources.getColor(R.color.blue))
            board[2][2].setBackgroundColor(resources.getColor(R.color.blue))

            return true
        }



        if (!board[0][2].text.equals("") &&
            board[0][2].text.toString().equals(board[1][1].text.toString()) &&
            board[0][2].text.toString().equals(board[2][0].text.toString())){

            board[0][2].setBackgroundColor(resources.getColor(R.color.blue))
            board[1][1].setBackgroundColor(resources.getColor(R.color.blue))
            board[2][0].setBackgroundColor(resources.getColor(R.color.blue))

            return true
        }




        return false
    }

    private fun player1Win(){
        player1Score++
        player1_score.text = "Player 1 : $player1Score"
        resetBoard()
        turn = !turn
        rounds = 0
    }

    private fun player2Win(){
        player2Score++
        player2_score.text = "Player 2 : $player2Score"
        resetBoard()
        turn = !turn
        rounds = 0
    }

    private fun draw(){
        resetBoard()
        rounds = 0
    }

    private fun resetBoard(){
        for (i in 0 until 3){
            for (j in 0 until 3){
                board[i][j].text = ""
                board[i][j].setBackgroundColor(resources.getColor(R.color.grey))

            }
        }
    }

    fun starttimer(){
        val timer = object : CountDownTimer(time,1000) {
            override fun onFinish() {

            }

            override fun onTick(millisUntilFinished: Long) {
                time = millisUntilFinished
            }

        }.start()

    }



}
