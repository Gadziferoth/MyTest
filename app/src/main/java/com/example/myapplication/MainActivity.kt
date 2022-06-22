package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.viewmodels.MainVM
import kotlin.random.Random

@SuppressLint("SetTextI18n")

class MainActivity : AppCompatActivity() {
    lateinit var btn1: Button
    lateinit var textV1: TextView
    lateinit var editT1: EditText
    lateinit var textSum: TextView
    lateinit var textTrue: TextView
    private lateinit var textFalse: TextView
    private lateinit var textPercent: TextView

    private var t = 0
    private var f = 0
    private var c = 0
    private val sum: Int
        get() = t + f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var b : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        btn1 = findViewById(R.id.button_1)
        textV1 = findViewById(R.id.textView_1)
        editT1 = findViewById(R.id.editText_1)
        textSum = findViewById(R.id.text_sum)
        textTrue = findViewById(R.id.text_true)
        textFalse = findViewById(R.id.text_false)
        textPercent = findViewById(R.id.text_perc)

        val popTrue = listOf("Отлично !", "Верно !", "Правильно !", "Прекрасно !")
        val popFalse =
            listOf("Упс, ошибочка", "Подумай еще раз", "Посчитай еще", "Еще одна попытка")

        // Global
        fun createExpPM10() {
            if (Random.nextBoolean()) {
                // Plus expression
                var a = Random.nextInt(1, 98)
                var b = Random.nextInt(1, (99 - a))
                c = a * 10 + b * 10
                textV1.text = "${a}0 + ${b}0  ="

            } else {
                // Minus expression
                var a = Random.nextInt(2, 99)
                var b = Random.nextInt(1, a)
                c = a * 10 - b * 10
                textV1.text = "${a}0 - ${b}0  ="
            }
        }

        fun createExpMD() {
            if (Random.nextBoolean()) {
                // Mult expression
                var a = Random.nextInt(1, 9)
                var b = Random.nextInt(1, 9)
                c = a * b
                textV1.text = "$a * $b  ="

            } else {
                // Div expression
                var a = Random.nextInt(1, 9)
                var b = Random.nextInt(1, 9)
                c = a * b
                textV1.text = "$c / $a  ="
                c = b
            }
        }

        fun createExpMD10() {
            if (Random.nextBoolean()) {
                // Mult expression
                var a = Random.nextInt(1, 9)
                var b = Random.nextInt(1, 9)
                c = a * 10 * b
                textV1.text = "${a}0 * $b  ="

            } else {
                // Div expression
                var a = Random.nextInt(1, 9)
                var b = Random.nextInt(1, 9)
                c = a * 10 * b
                textV1.text = "$c / $a  ="
                c = b * 10
            }
        }

        val listOfExp = listOf(::createExpMD10, ::createExpMD, ::createExpPM, ::createExpPM10)
        fun choose() {
            var expNo = Random.nextInt(listOfExp.count())
            var expFunction = listOfExp[expNo]
            expFunction()
        }

        choose()

        btn1.setOnClickListener {
            val answer = editT1.text.toString().toIntOrNull() ?: return@setOnClickListener

            if (answer == c) {
                editT1.text.clear()
                Toast.makeText(this, popTrue.random(), Toast.LENGTH_LONG).show()
                // listofExp[Random.nextInt(0, 3)]
                choose()
                textTrue.text = "Правильно: ${++t}"
                textSum.text = "Решено: $sum"
                textPercent.text = "% верных ответов: ${t * 100 / sum}"
            } else {
                Toast.makeText(this, popFalse.random(), Toast.LENGTH_LONG).show()
                textFalse.text = "Ошибки: ${++f}"
                textSum.text = "Решено: $sum"
                textPercent.text = "% верных ответов: ${t * 100 / sum}"
            }

        }
    }

    private fun createExpPM() {
        if (Random.nextBoolean()) {
            // Plus expression
            var a = Random.nextInt(1, 98)
            var b = Random.nextInt(1, (99 - a))
            c = a + b
            textV1.text = "$a + $b  ="
        } else {
            // Minus expression
            var a = Random.nextInt(2, 99)
            var b = Random.nextInt(1, a)
            c = a - b
            textV1.text = "$a - $b  ="
        }
    }


}