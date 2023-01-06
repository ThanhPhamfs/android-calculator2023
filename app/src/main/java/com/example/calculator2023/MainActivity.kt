package com.example.calculator2023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    var tvResult: TextView? = null
    var oldNumber: Double = 0.0
    var operater: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvResult = findViewById(R.id.tvResult)
    }

    fun calculatorButtonClick(view: View) {
//        Toast.makeText(this, "${}",Toast.LENGTH_LONG ).show()
        val buttonText = (view as? Button)?.text
        if (buttonText is String) {
            when (buttonText) {
                this.getString(R.string.summation) -> handlingProcessingButton(buttonText)
                this.getString(R.string.subtraction) -> handlingProcessingButton(buttonText)
                this.getString(R.string.clear) -> clearResult()
                this.getString(R.string.dot) -> handlingDotButton()
                this.getString(R.string.division) -> handlingProcessingButton(buttonText)
                this.getString(R.string.equation) -> handlingEquationButton()
                this.getString(R.string.multiplication) -> handlingProcessingButton(buttonText)
                else -> handlingDigitalButton(buttonText)
            }
        }
    }

    private fun handlingDigitalButton(number: CharSequence) {
//        Log.d("LOG", "$number")
        if (number == "0" && tvResult?.text.toString() == "0") {
            return
        }

        if (tvResult?.text.toString() == "0"){
            tvResult?.text = number
            return
        }

        tvResult?.append(number)
    }

    private fun handlingProcessingButton(operation: String) {
        val result = tvResult?.text.toString()
        if (result == "0"){
            return
        }

        operater = operation
        oldNumber = result.toDouble()
        tvResult?.text = "0"
    }

    private fun clearResult() {
        tvResult?.text = "0"
    }

    private fun handlingDotButton() {
        if (tvResult?.text?.contains(".") == true) {
            return
        }
        tvResult?.append(".")
    }

    private fun handlingEquationButton() {
        if (operater == null){
            return
        }
        var result: Double = 0.0
        when(operater){
            this.getString(R.string.summation) -> {
                result = oldNumber + tvResult?.text.toString().toDouble()
            }
            this.getString(R.string.subtraction) -> {
                result = oldNumber - tvResult?.text.toString().toDouble()
            }
            this.getString(R.string.division) -> {
                if (tvResult?.text.toString() == "0"){
                    tvResult?.text = "Infinity"
                    return
                }
                result = oldNumber / tvResult?.text.toString().toDouble()
            }
            this.getString(R.string.multiplication) -> {
                result = oldNumber * tvResult?.text.toString().toDouble()
            }
        }
        if (result == result.toInt().toDouble()){
            tvResult?.text = "${result.toInt()}"
        }else{
            tvResult?.text = "$result"
        }
        operater = null
        oldNumber = result
    }
}