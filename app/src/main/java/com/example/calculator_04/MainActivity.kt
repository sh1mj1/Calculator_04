package com.example.calculator_04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    // Mark -Properties/////////////////////////////////////////
    private val expressionTV: TextView by lazy {
        findViewById(R.id.expression_Tv)
    }
    private val resultTV: TextView by lazy {
        findViewById(R.id.result_Tv)
    }
    private var isOperator = false
    private var hasOperator = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // Mark -Help/////////////////////////////////////////
    fun btnClicked(v: View) {
        when (v.id) {
            R.id.Btn_0 -> numBtnClicked("0")
            R.id.Btn_1 -> numBtnClicked("1")
            R.id.Btn_2 -> numBtnClicked("2")
            R.id.Btn_3 -> numBtnClicked("3")
            R.id.Btn_4 -> numBtnClicked("4")
            R.id.Btn_5 -> numBtnClicked("5")
            R.id.Btn_6 -> numBtnClicked("6")
            R.id.Btn_7 -> numBtnClicked("7")
            R.id.Btn_8 -> numBtnClicked("8")
            R.id.Btn_9 -> numBtnClicked("9")
            R.id.Btn_plus -> opBtnClicked("+")
            R.id.Btn_minus -> opBtnClicked("-")
            R.id.Btn_multiply -> opBtnClicked("×")
            R.id.Btn_divide -> opBtnClicked("÷")
        }

    }

    private fun numBtnClicked(number: String) {

        if (isOperator) {
            expressionTV.append(" ")
        }
        isOperator = false

        // 숫자끼리 뭉쳐넣고 한칸 띄고 연산자 한칸 띄고 숫자로 배치할 것.
        // String 에서는 split 이라는 함수로 리스트에 넣어줌. split 에서 사용하는 구분자를 공백문자(space)로 할 것임.
        // 숫자가 들어왔을 대 expressionText 가 비어있을 때는 무조건 숫자 입력 가능
        // 무조건 마지막의 있는 expressionText 의 뒤에 붙은 항.
        val expressionText = expressionTV.text.split(" ")
        if (expressionText.isNotEmpty() && expressionText.last().length >= 15) {
            Toast.makeText(this, "숫자는 15자리 이상으로 입력할 수 없습니다.", Toast.LENGTH_SHORT).show()
            return
        } else if (number == "0" && expressionText.last().isEmpty()) {
            Toast.makeText(this, "0은 제일 앞에 올 수 없습니다..", Toast.LENGTH_SHORT).show()
        }
        expressionTV.append(number)

        // TODO: resultView에 실시간으로 연산 결과를 넣을 예정.
        resultTV.text = calculateExpression()
    }

    private fun opBtnClicked(operator: String) {
        if (expressionTV.text.isEmpty()) {
            return
        }
        when {
            isOperator -> {
                val text = expressionTV.text.toString()
                expressionTV.text = text.dropLast(1) + operator
            }
            hasOperator -> {
                Toast.makeText(this, "연산자는 한번만 사용할 수 있습니다.", Toast.LENGTH_SHORT).show()
                return
            }

            else -> {
                expressionTV.append(" " + operator)
            }
        }
        val ssb = SpannableStringBuilder(expressionTV.text)
        ssb.setSpan(
            ForegroundColorSpan(getColor(R.color.green)),
            expressionTV.text.length - 1,
            expressionTV.text.length,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
        expressionTV.text = ssb

        isOperator = true
        hasOperator = true

    }


    fun clearBtnClicked(v: View) {
        expressionTV.text = ""
        resultTV.text = ""
        isOperator = false
        hasOperator = false

    }

    fun historyBtnClicked(v: View) {

    }

    fun resultBtnClicked(v: View) {
        // 연산을 했던 것과 똑같이 해주면 댐.
        val expressionTexts = expressionTV.text.split(" ")
        if (expressionTV.text.isEmpty() || expressionTexts.size == 1) { return }
        if (expressionTexts.size != 3 && hasOperator) {
            Toast.makeText(this, "아직 완성되지 않은 수식입니다.", Toast.LENGTH_SHORT).show()
            return
        }
        if (expressionTexts[0].isNumber().not() || expressionTexts[2].isNumber().not()) { // 사실 이 오류가 발생한다는 것은 이전에 코드가 잘못되었다는 것이지만 이중 오류 제어
            Toast.makeText(this, "오류가 발생했습니다.", Toast.LENGTH_SHORT).show()
            return
        }

        val expressionText = expressionTV.text.toString()
        val resultText = calculateExpression()

        resultTV.text = ""
        expressionTV.text = resultText

        isOperator = false
        hasOperator = false
    }

    private fun calculateExpression(): String {
        val expressionTexts = expressionTV.text.split(" ")

        if(hasOperator.not() || expressionTexts.size != 3) {
            return ""
        } else if (expressionTexts[0].isNumber().not() || expressionTexts[2].isNumber().not()) {
            return ""
        }
        val exp1 = expressionTexts[0].toBigInteger()
        val exp2 = expressionTexts[2].toBigInteger()
        val op = expressionTexts[1]

        return when(op){
            "+" -> (exp1 + exp2).toString()
            "-" -> (exp1 - exp2).toString()
            "×" -> (exp1 * exp2).toString()
            "÷" -> (exp1 / exp2).toString()
            else -> ""
        }

    }



}

// String을 확장하는 확장함수. 앞에 개체(타입)를 넣으면 해당 타입 메소드를 만들 수 있다.!!!
// 함수형 프로그래밍에 굉장히 중요한 테크닉일 듯.

fun String.isNumber(): Boolean {
    return try {
        this.toBigInteger()
        true
    } catch (e: NumberFormatException){
        false
    }
}