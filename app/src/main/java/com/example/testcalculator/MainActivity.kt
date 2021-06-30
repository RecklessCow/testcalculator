package com.example.testcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import java.lang.NumberFormatException
import kotlinx.android.synthetic.main.activity_main.*

//private const val TAG = "mainActivity"
private const val OPERANDKEY = "operand1"
private const val PENDINGOPERATION = "pendingOperation"
private const val OPERANDSECONDKEY = "operand2"


class MainActivity : AppCompatActivity() {

    // variables to store operands
    private var operand1: Double? = null
    private var operand2: Double = 0.0
    private var pendingOperation = "="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //????????????????????????????????????
        val listener = View.OnClickListener { v ->
            val b = v as Button
                secondnum.append(b.text)
        }

        val clearMethod = View.OnClickListener {
            operand1 = null
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
        buttondot.setOnClickListener(listener)
        clearbutton.setOnClickListener(clearMethod)

        fun performOperation(value: Double, operation: String) {
            if (operand1 == null) {
                operand1 = value

            } else {
                operand2 = value

                if (pendingOperation == "=") {
                    pendingOperation = operation
                }

                when (pendingOperation) {
                    "=" -> operand1 = operand2
                    "/" -> operand1 = if (operand2 == 0.0) {
                        Double.NaN
                    } else {
                        operand1!! / operand2
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
            try {
                val value = secondnum.text.toString().toDouble()
                performOperation(value, op)
            } catch (e: NumberFormatException) {
                secondnum.text.clear()
            }
            pendingOperation = op
            operation.text = pendingOperation
        }


        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        val negation = View.OnClickListener {
            operand1 = operand1?.minus((operand1!! * 2))
            var x: Double? = null

            if(secondnum.text.isNotEmpty()){
                x = secondnum.text.toString().toDouble()
                x -= x * 2
            }

            if(operand1 != null){
                result.setText(operand1.toString())
            }else {
                secondnum.setText(x.toString())
            }
        }

        buttonequals.setOnClickListener(opListener)
        buttonplus.setOnClickListener(opListener)
        buttonminus.setOnClickListener(opListener)
        buttondevide.setOnClickListener(opListener)
        buttonmultiply.setOnClickListener(opListener)
        negButton.setOnClickListener(negation)

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        operand1?.let {
            outState.putDouble(OPERANDKEY, it)
        }
        outState.putDouble(OPERANDSECONDKEY, operand2)
        outState.putString(PENDINGOPERATION, pendingOperation)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
            operand1 = savedInstanceState.getDouble(OPERANDKEY)
            operand2 = savedInstanceState.getDouble(OPERANDSECONDKEY)
            operation.text = savedInstanceState.getString(PENDINGOPERATION)
        }
    }