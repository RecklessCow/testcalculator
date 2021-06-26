package com.example.testcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

private const val TAG = "mainactivity"

class MainActivity : AppCompatActivity() {
    //klir
    private val result by lazy(LazyThreadSafetyMode.NONE) { findViewById<EditText>(R.id.result) }
    private val secondnum by lazy(LazyThreadSafetyMode.NONE) { findViewById<EditText>(R.id.secondnum) }
    private val displayOperation by lazy(LazyThreadSafetyMode.NONE) { findViewById<TextView>(R.id.operation) }

    // variables to store operands
    private var operand1: Double? = null
    private var operand2: Double = 0.0
    private var pendingOperation = "="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //number buttons
        val button0: Button = findViewById(R.id.button0)
        val button1: Button = findViewById(R.id.button1)
        val button2: Button = findViewById(R.id.button2)
        val button3: Button = findViewById(R.id.button3)
        val button4: Button = findViewById(R.id.button4)
        val button5: Button = findViewById(R.id.button5)
        val button6: Button = findViewById(R.id.button6)
        val button7: Button = findViewById(R.id.button7)
        val button8: Button = findViewById(R.id.button8)
        val button9: Button = findViewById(R.id.button9)
        val buttonDot: Button = findViewById(R.id.buttondot)

        //operations buttons
        val buttonPlus: Button = findViewById(R.id.buttonplus)
        val buttonMinus: Button = findViewById(R.id.buttonminus)
        val buttonDevide: Button = findViewById(R.id.buttondevide)
        val buttonMultiply: Button = findViewById(R.id.buttonmultiply)
        val buttonEquals: Button = findViewById(R.id.buttonequals)
        val buttonclear: Button = findViewById(R.id.clearbutton)

        //????????????????????????????????????
        val listener = View.OnClickListener { v ->
            val b = v as Button
            secondnum.append(b.text)
        }

        val clearmethod = View.OnClickListener { v ->
            operand1 = 0.0
            operand2 = 0.0
            secondnum.text.clear()
            result.text.clear()
        }

        button0.setOnClickListener(listener)
        button1.setOnClickListener(listener)
        button2.setOnClickListener(listener)
        button3.setOnClickListener(listener)
        button4.setOnClickListener(listener)
        button5.setOnClickListener(listener)
        button6.setOnClickListener(listener)
        button7.setOnClickListener(listener)
        button8.setOnClickListener(listener)
        button9.setOnClickListener(listener)
        buttonDot.setOnClickListener(listener)
        buttonclear.setOnClickListener(clearmethod)

        fun performOperation(value: String, operation: String) {
            if(operand1 == null){
                operand1 = value.toDouble()

            }else {
                operand2 = value.toDouble()

                if(pendingOperation == "="){
                    pendingOperation = operation
                }

                when(pendingOperation){
                    "=" -> operand1 = operand2
                    "/" -> if(operand2 == 0.0){
                            operand1 = Double.NaN
                    }else{
                        operand1 = operand1!! / operand2
                    }
                    "*" -> operand1 = operand1!! * operand2
                    "+" -> operand1 = operand1!! + operand2
                    "-" -> operand1 = operand1!! - operand2
                }

            }
            result.setText(operand1.toString())
            secondnum.text.clear()
            operand2 = 0.0
        }

        val opListener = View.OnClickListener { v ->
            val op = (v as Button).text.toString()
            val value = secondnum.text.toString()
            if (value.isNotEmpty()) {
                performOperation(value, op)
            }
            pendingOperation = op
            displayOperation.text = pendingOperation
        }

        buttonEquals.setOnClickListener(opListener)
        buttonPlus.setOnClickListener(opListener)
        buttonMinus.setOnClickListener(opListener)
        buttonDevide.setOnClickListener(opListener)
        buttonMultiply.setOnClickListener(opListener)
    }
}