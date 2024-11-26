package com.example.cookbook

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.InputStreamReader

class milkshakee : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_milkshakee)

        val descriptionTextView: TextView = findViewById(R.id.description)
        val backButton: Button = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }

        // Чтение текста из res/raw/description.txt
        val textFromFile = readTextFromRaw(R.raw.milkshake)
        descriptionTextView.text = textFromFile
    }

    private fun readTextFromRaw(resourceId: Int): String {
        val stringBuilder = StringBuilder()
        try {
            val reader = BufferedReader(InputStreamReader(resources.openRawResource(resourceId)))
            var line = reader.readLine()
            while (line != null) {
                stringBuilder.append(line).append("\n")
                line = reader.readLine()
            }
            reader.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return stringBuilder.toString()
    }
}
