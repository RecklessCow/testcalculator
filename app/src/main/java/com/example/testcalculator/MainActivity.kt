    package com.example.testcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button

    private const val TAG  = "mainactivity"

    class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button7 = findViewById<Button>(R.id.button7)
        val button6 = findViewById<Button>(R.id.button6)
        val button5 = findViewById<Button>(R.id.button5)
        val button4 = findViewById<Button>(R.id.button4)
        val button3 = findViewById<Button>(R.id.button3)
        val button2 = findViewById<Button>(R.id.button2)
        val button1 = findViewById<Button>(R.id.button1)

        button7.setOnClickListener(){
            Log.d(TAG,"VABOzYLNEM")
        }
    }
}