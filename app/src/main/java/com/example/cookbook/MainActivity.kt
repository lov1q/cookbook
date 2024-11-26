package com.example.cookbook

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList: ArrayList<DataClass>
    private lateinit var imageList: Array<Int>
    private lateinit var titleList: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Список изображений
        imageList = arrayOf(
            R.drawable.ic_borsch,
            R.drawable.ic_cesar,
            R.drawable.ic_blinchik,
            R.drawable.ic_milkshake,
            R.drawable.ic_spagetti
        )

        // Список заголовков
        titleList = arrayOf(
            "Борщ",
            "Цезарь",
            "Блинчики",
            "Мол.Коктейл",
            "Спагетти"
        )

        // Настройка RecyclerView
        recyclerView = findViewById(R.id.recycleview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        dataList = arrayListOf()
        getData() // Заполняем данные
    }

    private fun getData() {
        for (i in imageList.indices) {
            val dataClass = DataClass(imageList[i], titleList[i])
            dataList.add(dataClass)
        }

        // Устанавливаем адаптер с обработчиком кликов
        recyclerView.adapter = AdapterClass(dataList) { selectedItem ->
            // Переход на активность в зависимости от выбранного элемента
            val intent = when (selectedItem.dataTitle) {
                "Борщ" -> Intent(this, borsh::class.java)
                "Цезарь" -> Intent(this, cesarr::class.java)
                "Блинчики" -> Intent(this, blinchik::class.java)
                "Мол.Коктейл" -> Intent(this, milkshakee::class.java)
                "Спагетти" -> Intent(this, spagettii::class.java)
                else -> Intent(this, cesarr::class.java) // На случай ошибки
            }

            // Передаем данные в выбранную активность
            intent.putExtra("ITEM_IMAGE", selectedItem.dataImage)
            intent.putExtra("ITEM_TITLE", selectedItem.dataTitle)
            startActivity(intent)
        }
    }
}
