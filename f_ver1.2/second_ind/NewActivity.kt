package com.example.second_ind

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

const val  EXTRA_MAS_TO_MAIN = "com.example.second_ind.mas_to_main"
const val  EXTRA_MAS_FROM_MAIN = "com.example.second_ind.mas_from_main"
class NewActivity : AppCompatActivity() {
    private lateinit var btnSave: Button
    private lateinit var edt1: EditText
    private lateinit var edt2: EditText
    private lateinit var edt3: EditText
    private lateinit var edt4: EditText
    private var masFromMain = arrayListOf<String>("")

    @SuppressLint("MissingInflatedId")
    companion object {
        fun newIntent(packageContext: Context, masFromMain: ArrayList<String>): Intent {
            return Intent(packageContext, NewActivity::class.java).apply {
                putExtra(EXTRA_MAS_FROM_MAIN, masFromMain)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        masFromMain = intent?.getStringArrayListExtra(EXTRA_MAS_FROM_MAIN)!!
        setContentView(R.layout.activity_new)
        edt1=findViewById(R.id.edt1)
        edt2=findViewById(R.id.edt2)
        edt3=findViewById(R.id.edt3)
        edt4=findViewById(R.id.edt4)
        edt1.requestFocus()
        btnSave=findViewById(R.id.btnSave)
        val names = mutableListOf<String> ("марка", "модель", "номер", "владелец")
        findViewById<TextView>(R.id.tv1).setText(names.get(0))
        findViewById<TextView>(R.id.tv2).setText(names.get(1))
        findViewById<TextView>(R.id.tv3).setText(names.get(2))
        findViewById<TextView>(R.id.tv4).setText(names.get(3))


        if (masFromMain?.get(0) == "true"){
            edt1.setText(""); edt2.setText(""); edt3.setText(""); edt4.setText("")
        }
        else{
            edt1.setText(masFromMain?.get(1)); edt2.setText(masFromMain?.get(2)); edt3.setText(masFromMain?.get(3)); edt4.setText(masFromMain?.get(4))
        }

        btnSave.setOnClickListener {
            if (validation()){
                val mas1 = arrayListOf<String>(edt1.text.toString(), edt2.text.toString(), edt3.text.toString(), edt4.text.toString())
                setResult(mas1)
            }
        }
    }

    private fun validation():Boolean{
        var flag_not_error = true
        if (edt4.text.isBlank()){
            edt4.requestFocus()
            edt4.setError("Необходимо ввести данные!")
            flag_not_error = false
        }
        if (edt3.text.isBlank()){
            edt3.requestFocus()
            edt3.setError("Необходимо ввести данные!")
            flag_not_error = false
        }
        if (edt2.text.isBlank()){
            edt2.requestFocus()
            edt2.setError("Необходимо ввести данные!")
            flag_not_error = false
        }
        if (edt1.text.isBlank()){
            edt1.requestFocus()
            edt1.setError("Необходимо ввести данные!")
            flag_not_error = false
        }
        return flag_not_error
    }

    private fun setResult(mas: ArrayList<String>){
        val data = Intent().apply {
            putStringArrayListExtra(EXTRA_MAS_TO_MAIN, mas)
        }
        setResult(Activity.RESULT_OK, data)
    }
}