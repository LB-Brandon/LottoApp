package com.brandon.lottoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import java.util.HashSet

val NUMBER_RANGE_1_TO_10 = 1..10
val NUMBER_RANGE_11_TO_20 = 11..20
val NUMBER_RANGE_21_TO_30 = 21..30
val NUMBER_RANGE_31_TO_40 = 31..40

class MainActivity : AppCompatActivity() {


    private val clearButton by lazy { findViewById<Button>(R.id.btn_clear) }
    private val addButton by lazy { findViewById<Button>(R.id.btn_add) }
    private val runButton by lazy { findViewById<Button>(R.id.btn_run) }
    private val numberPicker by lazy { findViewById<NumberPicker>(R.id.np_num) }

    private val numTextViewList: List<TextView> by lazy {
        listOf<TextView>(
            findViewById(R.id.tv_num1),
            findViewById(R.id.tv_num2),
            findViewById(R.id.tv_num3),
            findViewById(R.id.tv_num4),
            findViewById(R.id.tv_num5),
            findViewById(R.id.tv_num6),
        )
    }

    private var hasRun: Boolean = false
    private val selectedNumbers: HashSet<Int> = hashSetOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numberPicker.apply {
            minValue = 1
            maxValue = 45
        }

        initRunButton()
        initAddButton()
        initClearButton()
    }

    private fun initClearButton() {
        clearButton.setOnClickListener {
            selectedNumbers.clear()
            numTextViewList.forEach { it.isVisible = false }
            hasRun = false
            numberPicker.value = 1
        }
    }

    private fun initRunButton() {
        runButton.setOnClickListener {
            val randomNumbersWithPickedNumbers: List<Int> = getRandom()
            hasRun = true

            randomNumbersWithPickedNumbers.forEachIndexed { index, number ->
                val textView = numTextViewList[index]
                textView.text = number.toString()
                textView.isVisible = true
                setNumberBackgroundColorByRange(number, textView)
            }
        }
    }

    private fun getRandom(): List<Int> {
        val numbers = (1..45).filter { it !in selectedNumbers }
        return (selectedNumbers + numbers.shuffled().take(6 - selectedNumbers.size)).sorted()
    }

    private fun initAddButton() {
        addButton.setOnClickListener {
            when {
                hasRun -> makeToast("초기화 후 시도해주세요.")
                selectedNumbers.size >= 5 -> makeToast("숫자는 최대 5개까지 선택할 수 있습니다.")
                selectedNumbers.contains(numberPicker.value) -> makeToast("이미 존재하는 숫자입니다.")
                else -> {
                    val textView = numTextViewList[selectedNumbers.size]
                    textView.isVisible = true
                    textView.text = numberPicker.value.toString()

                    selectedNumbers.add(numberPicker.value)
                    setNumberBackgroundColorByRange(numberPicker.value, textView)
                    numberPicker.value += 1

                }
            }
        }
    }

    private fun setNumberBackgroundColorByRange(num: Int, textView: TextView) {
        val backgroundColor = when (num) {
            in NUMBER_RANGE_1_TO_10 -> R.drawable.circle_yellow
            in NUMBER_RANGE_11_TO_20 -> R.drawable.circle_blue
            in NUMBER_RANGE_21_TO_30 -> R.drawable.circle_red
            in NUMBER_RANGE_31_TO_40 -> R.drawable.circle_gray
            else -> R.drawable.circle_green
        }
        textView.background = ContextCompat.getDrawable(this, backgroundColor)
    }

    private fun makeToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


}