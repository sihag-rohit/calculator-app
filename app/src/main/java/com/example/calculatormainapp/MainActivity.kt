package com.example.calculatormainapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var display: TextView
    private var currentNumber = ""
    private var operator = ""
    private var operand1 = 0.0
    private var operand2 = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.display)

        val buttons = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9,
            R.id.btnac, R.id.btndelete, R.id.btnmod, R.id.btndiv,
            R.id.btnmul, R.id.btnsub, R.id.btnadd, R.id.btndot,
            R.id.btnequal
        )

        buttons.forEach { id ->
            findViewById<Button>(id).setOnClickListener { onButtonClick(it) }
        }
    }

    private fun onButtonClick(view: View) {
        when (view.id) {
            R.id.btn0 -> appendNumber("0")
            R.id.btn1 -> appendNumber("1")
            R.id.btn2 -> appendNumber("2")
            R.id.btn3 -> appendNumber("3")
            R.id.btn4 -> appendNumber("4")
            R.id.btn5 -> appendNumber("5")
            R.id.btn6 -> appendNumber("6")
            R.id.btn7 -> appendNumber("7")
            R.id.btn8 -> appendNumber("8")
            R.id.btn9 -> appendNumber("9")
            R.id.btndot -> appendNumber(".")
            R.id.btnac -> clear()
            R.id.btndelete -> delete()
            R.id.btnadd -> setOperator("+")
            R.id.btnsub -> setOperator("-")
            R.id.btnmul -> setOperator("*")
            R.id.btndiv -> setOperator("/")
            R.id.btnmod -> setOperator("%")
            R.id.btnequal -> calculateResult()
        }
    }

    private fun appendNumber(number: String) {
        currentNumber += number
        display.text = currentNumber
    }

    private fun setOperator(op: String) {
        if (currentNumber.isNotEmpty()) {
            operand1 = currentNumber.toDouble()
            currentNumber = ""
            operator = op
        }
    }

    private fun calculateResult() {
        if (currentNumber.isNotEmpty()) {
            operand2 = currentNumber.toDouble()
            val result = when (operator) {
                "+" -> operand1 + operand2
                "-" -> operand1 - operand2
                "*" -> operand1 * operand2
                "/" -> operand1 / operand2
                "%" -> operand1 % operand2
                else -> 0.0
            }
            display.text = result.toString()
            currentNumber = result.toString()
        }
    }

    private fun clear() {
        currentNumber = ""
        operator = ""
        operand1 = 0.0
        operand2 = 0.0
        display.text = ""
    }

    private fun delete() {
        if (currentNumber.isNotEmpty()) {
            currentNumber = currentNumber.dropLast(1)
            display.text = currentNumber
        }
    }
}