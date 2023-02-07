package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var input : TextView? = null
    var lastnumeric : Boolean = false
    var lastdot : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        input = findViewById(R.id.input)

    }

    fun ondigit(view: View) {
        input?.append((view as Button).text)
        lastnumeric = true
        lastdot = false
    }

    fun onclear(view : View){
        input?.text = ""
    }

    fun removelastdigit(view : View){
        var lastdigit = input?.text.toString()
        if(lastdigit.isNotEmpty()){
            input?.text = input?.text?.dropLast(1)
        }

    }

    fun ondotpoint(view: View){
        if (lastnumeric && !lastdot){
            input?.append(".")
            lastnumeric = false
            lastdot = true
        }

    }

    fun onoperator(view: View){
        input?.text?.let{
            if(lastnumeric && !isoperatoradded(it.toString())){
                input?.append((view as Button).text)
                lastnumeric = false
                lastdot = false
            }

        }
    }

    fun oneql(view: View){
        if(lastnumeric){
            var tvalue = input?.text.toString()
            var prefix = ""
            try{
                if(tvalue.startsWith("-")){
                    prefix = "-"
                    tvalue = tvalue.substring(1)
                }

                if(tvalue.contains("-")){
                    val splitvalue = tvalue.split("-")

                    var one = splitvalue[0]
                    var two = splitvalue[1]

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    input?.text = removezeroafterdot((one.toDouble() - two.toDouble()).toString())


                }else if(tvalue.contains("+")){
                    val splitvalue = tvalue.split("+")

                    var one = splitvalue[0]
                    var two = splitvalue[1]

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    input?.text = removezeroafterdot((one.toDouble() + two.toDouble()).toString())

                }else if(tvalue.contains("/")){
                    val splitvalue = tvalue.split("/")

                    var one = splitvalue[0]
                    var two = splitvalue[1]

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    input?.text = removezeroafterdot((one.toDouble() / two.toDouble()).toString())

                }else if(tvalue.contains("*")){
                    val splitvalue = tvalue.split("*")

                    var one = splitvalue[0]
                    var two = splitvalue[1]

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    input?.text = removezeroafterdot((one.toDouble() * two.toDouble()).toString())

                }else if(tvalue.contains("%")){
                    val splitvalue = tvalue.split("%")

                    var one = splitvalue[0]
                    var two = splitvalue[1]

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    input?.text = removezeroafterdot((one.toDouble() % two.toDouble()).toString())

                }
            }catch (e: java.lang.ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private fun removezeroafterdot(result : String ): String {
        var value = result
        if(result.contains(".0"))
            value = result.substring(0 , result.length - 2)

        return value

    }



    private fun isoperatoradded(value : String) : Boolean{
        return if (value.startsWith("-")){
            false
        }
        else{
            value.contains("/")
                    ||value.contains("+")
                    ||value.contains("*")
                    ||value.contains("-")
        }
    }
}