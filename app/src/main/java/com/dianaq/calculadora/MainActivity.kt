package com.dianaq.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.TextKeyListener.clear
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.Math.sqrt

class MainActivity : AppCompatActivity() {

    private lateinit var resultTv: TextView
    private var input: Double = 0.0
    private var currentOperator: String = ""
    private var isOperatorClicked: Boolean = false


//    val button = findViewById<Button>(R.id.btn) // Obtener una referencia al botón mediante su ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultTv = findViewById(R.id.resultTv)


        val numberButtons = listOf<Button>(
            findViewById(R.id.btn0),
            findViewById(R.id.btn1),
            findViewById(R.id.btn2),
            findViewById(R.id.btn3),
            findViewById(R.id.btn4),
            findViewById(R.id.btn5),
            findViewById(R.id.btn6),
            findViewById(R.id.btn7),
            findViewById(R.id.btn8),
            findViewById(R.id.btn9)
        )

        for (button in numberButtons) {
            button.setOnClickListener {
                appendNumber(button.text.toString())
            }
        }

        val operatorButtons = listOf<Button>(
            findViewById(R.id.btnPorcentaje),
            findViewById(R.id.btnPlus),
            findViewById(R.id.btnSubtract),
            findViewById(R.id.btnMultiply),
            findViewById(R.id.btnRaiz),
            findViewById(R.id.btnDivide)
        )
        
        for (button in operatorButtons) {
            button.setOnClickListener {
                setOperator(button.text.toString())
            }
        }

        // Asignar el listener de clic al botón decimales
        val btnDecimal = findViewById<Button>(R.id.btnDecimal)
        btnDecimal.setOnClickListener {
            if (!resultTv.text.contains('.')) {
                resultTv.append(".")
            }
        }

        // Asignar el listener de clic al botón igual
        val btnEquals = findViewById<Button>(R.id.btnEquals)
        btnEquals.setOnClickListener {
            calculateResult()
        }
        // Asignar el listener de clic al botón borrar
        val btnClear = findViewById<Button>(R.id.btnClear)
        btnClear.setOnClickListener {
            clearInput()
        }
    }

    private fun appendNumber(number: String) {
        if (isOperatorClicked) {
            resultTv.setText(number)
            isOperatorClicked = false
        } else {
            resultTv.append(number)
        }
    }

    private fun setOperator(operator: String) {
        if (currentOperator.isNotEmpty()) {
            calculateResult()
        }
        input = resultTv.text.toString().toDouble()
        currentOperator = operator
        isOperatorClicked = true
    }
    private fun calculateResult() {
        val secondNumber = resultTv.text.toString().toDouble()
        when (currentOperator) {
            "+" -> input += secondNumber
            "-" -> input -= secondNumber
            "*" -> input *= secondNumber
            "/" -> input /= secondNumber
            "√" -> input = sqrt(secondNumber)
            "%" -> input = (input / 100) * secondNumber
        }
        resultTv.setText(input.toString())
        currentOperator = ""
    }
    private fun clearInput() {
        resultTv.setText("")
        input = 0.0
        currentOperator = ""
    }
}