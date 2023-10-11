package com.example.second_ind.models

import androidx.lifecycle.ViewModel
import com.example.second_ind.data.Auto

class MainActivityModel: ViewModel() {
    val autoBanks = mutableListOf<Auto>(
        Auto("Audi", "A5", "В821НН", "Беляев Дмитрий Викторович"),
        Auto("Ford", "Bronco", "Б956НХ", "Бобров Николай Сергеевич")
    )
    private val names = mutableListOf<String> ("марка", "модель", "номер", "владелец")

    var s = autoBanks.size
    private var currentIndex = 0

    val t1: String
        get() = autoBanks[currentIndex].text1
    val t2: String
        get() = autoBanks[currentIndex].text2
    val t3: String
        get() = autoBanks[currentIndex].text3
    val t4: String
        get() = autoBanks[currentIndex].text4

    val currentObj: String
        get() =
            if (s>0) "${names.get(0)}: ${t1}" +
                    "\n${names.get(1)}: ${t2}" +
                    "\n${names.get(2)}: ${t3}" +
                    "\n${names.get(3)}: ${t4}"
            else "Нет записей!"

    fun moveToNext(){
        currentIndex = (currentIndex + 1) % s
    }

    fun moveToPrev(){
        currentIndex = (s + currentIndex - 1) % s
    }

    fun add_to_list(mas: ArrayList<String>){
        val auto_obj = Auto(mas[0], mas[1],mas[2],mas[3])
        if (!(auto_obj in autoBanks)) {
            autoBanks.add(auto_obj)
            s += 1
            currentIndex = s - 1
        }
    }

    fun save_edit(mas: ArrayList<String>){
        autoBanks[currentIndex] = Auto(mas[0], mas[1],mas[2],mas[3])
    }

    fun del(){
        autoBanks.removeAt(currentIndex)
        if (currentIndex == s-1) currentIndex -= 1
        s -= 1
        if (s==0) currentIndex = 0
    }
}