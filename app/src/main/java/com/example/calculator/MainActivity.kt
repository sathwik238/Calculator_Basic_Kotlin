package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException


class MainActivity : AppCompatActivity() {

   var lastDecimal:Boolean=false
    var lastNumeric:Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View) {
        val tvInput = findViewById<TextView>(R.id.tvInput)
            tvInput.append((view as Button).text)
        lastNumeric=true

        }
    fun onClear(view:View){
        val tvInput = findViewById<TextView>(R.id.tvInput)
        tvInput.text=""
        lastDecimal=false
        lastNumeric=false
    }

    fun onDecimal(view:View){
        val tvInput = findViewById<TextView>(R.id.tvInput)
        if(lastNumeric && !lastDecimal){
            tvInput.append(".")
            lastDecimal=true
            lastNumeric=false

        }
    }

    private fun isOperatorAdded(value:String):Boolean{
        return if(value.startsWith("-")){
            false
        }
        else{
            value.contains("+") || value.contains("-") || value.contains("*") || value.contains("/")
        }
    }

    fun onOperator(view:View){
        val tvInput = findViewById<TextView>(R.id.tvInput)
        if(lastNumeric && !isOperatorAdded(tvInput.text.toString())){
            tvInput.append((view as Button).text)
            lastDecimal=false
            lastNumeric=false
        }
    }

    fun onEqual(view:View){
        var tvInput=findViewById<TextView>(R.id.tvInput)
        if(lastNumeric){
            var tvValue=tvInput.text.toString()
            var prefix=""
            try{
                if(tvValue.startsWith("-")){
                    prefix="-"
                    tvValue=tvValue.substring(1)
                }
                if(tvValue.contains("-")){
                    var result:Any
                    val splitValue=tvValue.split("-")
                    var one=splitValue[0]
                    var two=splitValue[1]

                    if(!prefix.isEmpty()){
                        one=prefix+one
                    }
                    result=one.toDouble()-two.toDouble()
                    result=result.toString()
                    result=removeZeroes(result)
                    tvInput.text=result

                }
                else if(tvValue.contains("x")){
                    var result:Any
                    val splitValue=tvValue.split("x")
                    var one=splitValue[0]
                    var two=splitValue[1]

                    if(!prefix.isEmpty()){
                        one=prefix+one
                    }
                    result=one.toDouble()*two.toDouble()
                    result=result.toString()
                    result=removeZeroes(result)
                    tvInput.text=result

                }
                else if(tvValue.contains("+")){
                    var result:Any
                    val splitValue=tvValue.split("+")
                    var one=splitValue[0]
                    var two=splitValue[1]

                    if(!prefix.isEmpty()){
                        one=prefix+one
                    }
                    result=one.toDouble()+two.toDouble()
                    result=result.toString()
                    result=removeZeroes(result)
                    tvInput.text=result

                }
                else if(tvValue.contains("%")){
                    var result:Any
                    val splitValue=tvValue.split("%")
                    var one=splitValue[0]
                    var two=splitValue[1]

                    if(!prefix.isEmpty()){
                        one=prefix+one
                    }
                    result=one.toDouble()%two.toDouble()
                    result=result.toString()
                    result=removeZeroes(result)
                    tvInput.text=result

                }
                else if(tvValue.contains("/")){
                    var result:Any
                    val splitValue=tvValue.split("/")
                    var one=splitValue[0]
                    var two=splitValue[1]

                    if(!prefix.isEmpty()){
                        one=prefix+one
                    }
                    result=one.toDouble()/two.toDouble()
                    result=result.toString()
                    result=removeZeroes(result)
                    tvInput.text=result

                }
            }catch (e : ArithmeticException){
                tvInput.text="Invalid Operation"
            }

        }

    }

    private fun removeZeroes(result:String):String{
        return result.replace("0*$".toRegex(), "").replace("\\.$".toRegex(), "")
    }
}
