package com.example.calcu

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var firstNumber: EditText
    private lateinit var secondNumber: EditText
    private lateinit var displayResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firstNumber = findViewById(R.id.firstNumber)
        secondNumber = findViewById(R.id.secondNumber)
        displayResult = findViewById(R.id.displayResult)

        findViewById<Button>(R.id.btnAdd).setOnClickListener { executeCalculation('+') }
        findViewById<Button>(R.id.btnSubtract).setOnClickListener { executeCalculation('-') }
        findViewById<Button>(R.id.btnMultiply).setOnClickListener { executeCalculation('*') }
        findViewById<Button>(R.id.btnDivide).setOnClickListener { executeCalculation('/') }
    }

    private fun executeCalculation(operator: Char) {
        val num1 = firstNumber.text.toString().toDoubleOrNull()
        val num2 = secondNumber.text.toString().toDoubleOrNull()

        if (num1 != null && num2 != null) {
            val calcResult = when (operator) {
                '+' -> num1 + num2
                '-' -> num1 - num2
                '*' -> num1 * num2
                '/' -> if (num2 != 0.0) num1 / num2 else "Division Error"
                else -> "Invalid Operation"
            }
            displayResult.text = calcResult.toString()
        } else {
            displayResult.text = "Invalid Numbers"
        }
    }
}