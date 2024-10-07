package com.example.bottomnavybanez

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class CalcuFragment : Fragment() {
    private var number1EditText: EditText? = null
    private var number2EditText: EditText? = null
    private var addBtn: Button? = null
    private var subtractBtn: Button? = null
    private var multiplyBtn: Button? = null
    private var divideBtn: Button? = null
    private var resultTextView: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_calcu, container, false)

        // Initialize views
        number1EditText = view.findViewById(R.id.number1EditText)
        number2EditText = view.findViewById(R.id.number2EditText)
        addBtn = view.findViewById(R.id.addBtn)
        subtractBtn = view.findViewById(R.id.subtractBtn)
        multiplyBtn = view.findViewById(R.id.multiplyBtn)
        divideBtn = view.findViewById(R.id.divideBtn)
        resultTextView = view.findViewById(R.id.resultTextView)

        // Set click listeners for buttons
        addBtn?.setOnClickListener { performCalculation('+') }
        subtractBtn?.setOnClickListener { performCalculation('-') }
        multiplyBtn?.setOnClickListener { performCalculation('*') }
        divideBtn?.setOnClickListener { performCalculation('/') }

        return view
    }

    private fun performCalculation(operator: Char) {
        val num1 = number1EditText?.text?.toString()?.toDoubleOrNull()
        val num2 = number2EditText?.text?.toString()?.toDoubleOrNull()

        if (num1 == null || num2 == null) {
            resultTextView?.text = "Please enter valid numbers"
            return
        }

        var result = 0.0
        when (operator) {
            '+' -> result = num1 + num2
            '-' -> result = num1 - num2
            '*' -> result = num1 * num2
            '/' -> if (num2 != 0.0) {
                result = num1 / num2
            } else {
                resultTextView?.text = "Error: Division by Zero"
                return
            }
        }

        resultTextView?.text = result.toString()
    }
}
