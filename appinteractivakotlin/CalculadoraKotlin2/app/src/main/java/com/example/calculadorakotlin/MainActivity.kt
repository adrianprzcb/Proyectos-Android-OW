package com.example.calculadorakotlin

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , View.OnClickListener {

    private var firstNum = 0.0
    private var secondNum = 0.0
    private var operation: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        operation = null
        btn0.setOnClickListener(this)
        btn1.setOnClickListener(this)
        btn2.setOnClickListener(this)
        btn3.setOnClickListener(this)
        btn4.setOnClickListener(this)
        btn5.setOnClickListener(this)
        btn6.setOnClickListener(this)
        btn7.setOnClickListener(this)
        btn8.setOnClickListener(this)
        btn9.setOnClickListener(this)
        btnComma.setOnClickListener(this)
        btnDel.setOnClickListener(this)
        btnPlus.setOnClickListener(this)
        btnMinus.setOnClickListener(this)
        btnMulti.setOnClickListener(this)
        btnDiv.setOnClickListener(this)
        btnEquals.setOnClickListener(this)
        
    }

    override fun onClick(view: View?) {
        when(view){
            btn0 -> onNumberPressed("0")
            btn1 -> onNumberPressed("1")
            btn2 -> onNumberPressed("2")
            btn3 -> onNumberPressed("3")
            btn4 -> onNumberPressed("4")
            btn5 -> onNumberPressed("5")
            btn6 -> onNumberPressed("6")
            btn7 ->onNumberPressed("7")
            btn8 -> onNumberPressed("8")
            btn9 -> onNumberPressed("9")
            btnComma -> onNumberPressed(".")
            btnDiv -> onOperationPressed("/")
            btnDel -> onClearPressed()
            btnPlus -> onOperationPressed("+")
            btnMinus -> onOperationPressed("-")
            btnMulti -> onOperationPressed("x")
            btnEquals -> onEqualPressed()
        }
    }

    private fun onNumberPressed(number: String){
            renderScreen(number)
            checkOperation()

    }
    private fun renderScreen(number: String){

        val result = if(screen.text == "0" && number != ".")
            number
        else if (screen.text.contains(".") && number == ".")
            screen.text.toString()
        else
            "${screen.text}$number"

        screen.text = result
    }

private var sec: String? = null
    private fun checkOperation(){
        try {
            if (operation == null)
                firstNum = screen.text.toString().toDouble()
            else
                sec = screen.text.toString().substring(firstNum.toString().length + 2).trim()
            secondNum = sec?.toDouble() ?: 0.0
        }catch (e: Exception){
            Toast.makeText(this, "Don't even try it", Toast.LENGTH_SHORT).show()
        }
    }



    private fun onOperationPressed(symbol : String){
        try {


        this.operation = symbol
        firstNum = screen.text.toString().toDouble()

        screen.text = "${firstNum} $symbol "
        }catch (e: Exception){
            Toast.makeText(this, "Don't even try it", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onEqualPressed() {
        val result = when (operation) {
        "+" -> firstNum + secondNum
        "-" -> firstNum - secondNum
        "x" -> firstNum * secondNum
        "/" -> firstNum / secondNum
        else -> "0"
    }
        try{
        operation = null
        firstNum = result as Double

        screen.text = if(result.toString().endsWith(".0")){
            result.toString().replace(".0", "")
        }else{
            "%.2f".format(result).replace(",", ".")
        }}
        catch (e : Exception){
        e.printStackTrace()
        }
    }

    private fun onClearPressed(){
        screen.text="0"
        firstNum = 0.0
        secondNum = 0.0
    }

}