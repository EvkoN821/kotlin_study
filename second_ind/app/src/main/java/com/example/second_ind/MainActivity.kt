package com.example.second_ind

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.example.second_ind.models.MainActivityModel

class MainActivity : AppCompatActivity() {
    private lateinit var btnAdd: Button
    private lateinit var btnEdit: Button
    private lateinit var btnDelete: Button
    private lateinit var nextButton: ImageButton
    private lateinit var prevButton: ImageButton
    private lateinit var textView_info: TextView

    private val viewModel: MainActivityModel by lazy {
        val provider = ViewModelProvider(this)
        provider.get(MainActivityModel::class.java)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnAdd=findViewById(R.id.btnAdd)
        btnEdit=findViewById(R.id.btnEdit)
        btnDelete=findViewById(R.id.btnDelete)
        nextButton=findViewById(R.id.btnNext)
        prevButton=findViewById(R.id.btnPrev)
        textView_info=findViewById(R.id.tv_info)

        var flag = true
        updateInfo()

        btnDelete.setOnClickListener{
            if (viewModel.s>0) {
                viewModel.del()
                updateInfo()
            }
            else{
                val message = "Нет записей для удаления"
                Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
            }
        }

        nextButton.setOnClickListener{
            if (viewModel.s > 0) {
                viewModel.moveToNext()
                updateInfo()
            }
        }

        prevButton.setOnClickListener{
            if (viewModel.s > 0) {
                viewModel.moveToPrev()
                updateInfo()
            }
        }

        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            if (result.resultCode == Activity.RESULT_OK){
                val data: Intent? = result.data
                val result  = data?.getStringArrayListExtra(EXTRA_MAS_TO_MAIN)
                if (result != null)
                    if (flag)
                        viewModel.add_to_list(result)
                    else
                        viewModel.save_edit(result)
                updateInfo()
            }
        }

        btnAdd.setOnClickListener {
            flag = true
            val mas1 = arrayListOf<String>("true")
            val intent = NewActivity.newIntent(this@MainActivity,mas1)
            resultLauncher.launch(intent)
            updateInfo()
        }

        btnEdit.setOnClickListener{
            if (viewModel.s>0) {
                flag = false
                val mas1 = arrayListOf<String>("false", viewModel.t1, viewModel.t2, viewModel.t3, viewModel.t4)
                val intent = NewActivity.newIntent(this@MainActivity, mas1)
                resultLauncher.launch(intent)
                updateInfo()
            }
            else{
                val message = "Нет записей для редактирования"
                Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateInfo(){
        val currentInfo = viewModel.currentObj
        textView_info.setText(currentInfo)
    }
}