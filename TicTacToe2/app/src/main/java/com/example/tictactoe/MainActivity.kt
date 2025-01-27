package com.example.tictactoe

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.view.View


class MainActivity : AppCompatActivity() {

    private lateinit var tvTurn: TextView

    private lateinit var btn11: Button
    private lateinit var btn12: Button
    private lateinit var btn13: Button
    private lateinit var btn21: Button
    private lateinit var btn22: Button
    private lateinit var btn23: Button
    private lateinit var btn31: Button
    private lateinit var btn32: Button
    private lateinit var btn33: Button
    private lateinit var btnPlayAgain: Button

    private var isXTurn = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvTurn = findViewById(R.id.tvTurn)

        btn11 = findViewById(R.id.btn11)
        btn12 = findViewById(R.id.btn12)
        btn13 = findViewById(R.id.btn13)
        btn21 = findViewById(R.id.btn21)
        btn22 = findViewById(R.id.btn22)
        btn23 = findViewById(R.id.btn23)
        btn31 = findViewById(R.id.btn31)
        btn32 = findViewById(R.id.btn32)
        btn33 = findViewById(R.id.btn33)
        btnPlayAgain = findViewById(R.id.btnPlayAgain)

        val buttons = listOf(
            btn11, btn12, btn13,
            btn21, btn22, btn23,
            btn31, btn32, btn33
        )

        for (button in buttons) {
            button.setOnClickListener { onButtonClick(button) }
        }

        btnPlayAgain.setOnClickListener {
            resetGame()
        }

        btnPlayAgain.visibility = View.GONE
    }

    private fun onButtonClick(button: Button) {
        if (button.text.isNotEmpty()) return

        button.text = if (isXTurn) "X" else "O"

        if (checkWinner()) {
            tvTurn.text = if (isXTurn) "Player X Wins!" else "Player O Wins!"
            disableButtons()
            btnPlayAgain.visibility = View.VISIBLE
        } else if (isBoardFull()) {
            tvTurn.text = "It's a Tie!"
            btnPlayAgain.visibility = View.VISIBLE
        } else {
            isXTurn = !isXTurn
            tvTurn.text = if (isXTurn) "Turn X" else "Turn O"
        }
    }



    private fun checkWinner(): Boolean {
        val grid = arrayOf(
            arrayOf(btn11, btn12, btn13),
            arrayOf(btn21, btn22, btn23),
            arrayOf(btn31, btn32, btn33)
        )

        for (i in 0..2) {
            if ((grid[i][0].text == grid[i][1].text && grid[i][1].text == grid[i][2].text && grid[i][0].text.isNotEmpty()) || // Row
                (grid[0][i].text == grid[1][i].text && grid[1][i].text == grid[2][i].text && grid[0][i].text.isNotEmpty())    // Column
            ) {
                return true
            }
        }

        if ((grid[0][0].text == grid[1][1].text && grid[1][1].text == grid[2][2].text && grid[0][0].text.isNotEmpty()) || // Top-left to bottom-right
            (grid[0][2].text == grid[1][1].text && grid[1][1].text == grid[2][0].text && grid[0][2].text.isNotEmpty())    // Top-right to bottom-left
        ) {
            return true
        }

        return false
    }

    private fun isBoardFull(): Boolean {
        val buttons = listOf(
            btn11, btn12, btn13,
            btn21, btn22, btn23,
            btn31, btn32, btn33
        )

        for (button in buttons) {
            if (button.text.isEmpty()) return false
        }
        return true
    }

    private fun disableButtons() {
        val buttons = listOf(
            btn11, btn12, btn13,
            btn21, btn22, btn23,
            btn31, btn32, btn33
        )

        for (button in buttons) {
            button.isEnabled = false
        }
    }

    private fun resetGame() {
        // Reset all buttons
        val buttons = listOf(
            btn11, btn12, btn13,
            btn21, btn22, btn23,
            btn31, btn32, btn33
        )

        for (button in buttons) {
            button.text = ""
            button.isEnabled = true
        }

        isXTurn = true
        tvTurn.text = "Turn X"

        btnPlayAgain.visibility = View.GONE
    }

}
