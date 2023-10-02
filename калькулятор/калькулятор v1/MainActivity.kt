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
        var flag_error =""

        fun validation (operand: String, left: String, right: String){
            flag_error = ""
            if (operand.isBlank()) {
                edtOp.requestFocus()
                edtOp.setError("необходимо ввести знак действия")
                flag_error = "!"
            }
            if (left.isBlank() or (left.filter { it == '.' }.count() > 1)) {
                edtLeft.requestFocus()
                edtLeft.setError("необходимо заполнить поле корректными данными")
                flag_error = "!"
            }
            if (right.isBlank() or (right.filter { it == '.' }.count() > 1)) {
                edtRight.requestFocus()
                edtRight.setError("необходимо заполнить поле корректными данными")
                flag_error = "!"
            }
        }

        fun calculate(){
            val operand = edtOp.text.toString()
            val left = edtLeft.text.toString()
            val right = edtRight.text.toString()
            validation(operand, left, right)
            if (flag_error != "!") {
                var s = 0.0F
                if (operand == "+")
                    s = left.toFloat() + right.toFloat()
                else if (operand == "-")
                    s = left.toFloat() - right.toFloat()
                else if (operand == "/")
                    s = left.toFloat() / right.toFloat()
                else if (operand == "*")
                    s = left.toFloat() * right.toFloat()

                if (abs(s - s.toInt()) > 0.000001)
                    res = s.toString()
                else
                    res = s.toInt().toString()
                edtOp.setText("")
                edtRight.setText("")
            }
        }


        val cl = View.OnClickListener {
            edtLeft.setError(null); edtRight.setError(null)
            if (edtOp.text.isBlank()) {
                edtLeft.requestFocus()
                edtLeft.setText("${edtLeft.text.toString()}${(it as Button).text}")
            }
            else {
                edtRight.requestFocus()
                edtRight.setText("${edtRight.text.toString()}${(it as Button).text}")
            }
            edtRes.setText("")
        }


        val cl_op = View.OnClickListener {
            edtRight.requestFocus()
            edtOp.setError(null)
            if (!edtOp.text.isBlank() and !edtRight.text.isBlank() and !edtLeft.text.isBlank()) {
                calculate()
                if (flag_error != "!") {
                    edtLeft.setError(null)
                    edtLeft.setText(res)
                    edtRight.requestFocus()
                }
            }
            edtOp.setText("${(it as Button).text}")
            if (edtLeft.text.toString() == "") {
                edtLeft.setText(res)
                edtRight.requestFocus()
                edtLeft.setError(null)
            }
            edtRes.setText("")
        }

        val cl_res = View.OnClickListener {
            calculate()
            if (flag_error != "!") {
                edtRes.setText(res)
                edtLeft.setText("")
                edtLeft.requestFocus()
            }
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
