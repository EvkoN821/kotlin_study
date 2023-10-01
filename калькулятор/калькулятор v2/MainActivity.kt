package com.example.first_ind

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    private lateinit var edtLeft: EditText
    private lateinit var edtRight: EditText
    private lateinit var edtOp: TextView
    private lateinit var edtRes: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edtLeft = findViewById(R.id.et_left)
        edtRight = findViewById(R.id.et_right)
        edtOp = findViewById(R.id.tv_op)
        edtRes = findViewById(R.id.tv_res)

        var res = ""

        fun validation (operand: String, left: String, right: String){
            res = ""
            if (operand.isBlank()) {
                edtOp.requestFocus()
                edtOp.setError("необходимо ввести знак действия")
                res = "!"
            }
            if (left.isBlank() or (left.filter { it == '.' }.count() > 1)) {
                edtLeft.requestFocus()
                edtLeft.setError("необходимо заполнить поле корректными данными")
                res = "!"
            }
            if (right.isBlank() or (right.filter { it == '.' }.count() > 1)) {
                edtRight.requestFocus()
                edtRight.setError("необходимо заполнить поле корректными данными")
                res = "!"
            }
        }

        fun calculate(){
            val operand = edtOp.text.toString()
            val left = edtLeft.text.toString()
            val right = edtRight.text.toString()
            validation(operand, left, right)
            if (res != "!"){
                var s = 0.0F
                if (operand == "+")
                    s = left.toFloat() + right.toFloat()
                else if (operand == "-")
                    s = left.toFloat() - right.toFloat()
                else if (operand == "/")
                    s = left.toFloat() / right.toFloat()
                else if (operand == "*")
                    s = left.toFloat() * right.toFloat()
                if (abs(s-s.toInt()) > 0.000001)
                    res = s.toString()
                else
                    res = s.toInt().toString()
            }
        }

        val cl = View.OnClickListener {
            if (!edtRes.text.isBlank()){
                edtLeft.setText(""); edtRight.setText(""); edtRes.setText("");  edtOp.setText("")
            }
            edtLeft.setError(null); edtRight.setError(null)
            if (edtOp.text.isBlank())
                edtLeft.setText("${edtLeft.text.toString()}${(it as Button).text}")
            else
                edtRight.setText("${edtRight.text.toString()}${(it as Button).text}")
        }

        val cl_op = View.OnClickListener {
            edtOp.setError(null)
            if (!edtRes.text.isBlank()){
                edtLeft.setText(res)
                edtRight.setText(""); edtRes.setText("")
            }
            else if (!edtLeft.text.isBlank() and !edtRight.text.isBlank() and !edtOp.text.isBlank()) {
                calculate()
                if (res != "!"){
                    edtLeft.setText(res)
                    edtRight.setText("")
                }
            }
            edtOp.setText("${(it as Button).text}")
        }

        val cl_res = View.OnClickListener {
            calculate()
            if (res != "!")
                edtRes.setText("=   " + res)
        }

        findViewById<Button>(R.id.b_plus).setOnClickListener(cl_op)
        findViewById<Button>(R.id.b_div).setOnClickListener(cl_op)
        findViewById<Button>(R.id.b_dif).setOnClickListener(cl_op)
        findViewById<Button>(R.id.b_mul).setOnClickListener(cl_op)
        findViewById<Button>(R.id.b_res).setOnClickListener(cl_res)
        findViewById<Button>(R.id.b1).setOnClickListener(cl)
        findViewById<Button>(R.id.b2).setOnClickListener(cl)
        findViewById<Button>(R.id.b3).setOnClickListener(cl)
        findViewById<Button>(R.id.b4).setOnClickListener(cl)
        findViewById<Button>(R.id.b5).setOnClickListener(cl)
        findViewById<Button>(R.id.b6).setOnClickListener(cl)
        findViewById<Button>(R.id.b7).setOnClickListener(cl)
        findViewById<Button>(R.id.b8).setOnClickListener(cl)
        findViewById<Button>(R.id.b9).setOnClickListener(cl)
        findViewById<Button>(R.id.b0).setOnClickListener(cl)
        findViewById<Button>(R.id.b_fr).setOnClickListener(cl)
    }
}