package com.example.json_app_currency

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import retrofit2.Call
import android.view.View
import android.widget.*
import retrofit2.Response
import retrofit2.Callback

class MainActivity : AppCompatActivity() {
    private var myC: Converence? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val theDate = findViewById<View>(R.id.forDate) as TextView
        val inputNum = findViewById<View>(R.id.editTextNumber) as EditText
        val allCurrencies = findViewById<View>(R.id.spinner) as Spinner
        val convertDo = findViewById<View>(R.id.button) as Button

        val myArrayCurrency = arrayListOf("ada", "aed", "afn", "all", "amd", "ang", "aoa", "ars", "aud", "awg", "azn")
        var myTta: Int = 0

        if (allCurrencies != null){
            val myAdapt = ArrayAdapter(
                this, android.R.layout.simple_spinner_item, myArrayCurrency
            )
            allCurrencies.adapter = myAdapt
            allCurrencies.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>, view:View, position: Int, id:Long){
                    myTta = position
                }
                override fun onNothingSelected(parent: AdapterView<*>) {
                }
            }
        }
        convertDo.setOnClickListener{
            var chosenM = inputNum.text.toString().toFloat()
            var chosenCurrency: Float = chosenM
            getCurrency(onResult = {
                myC = it
                when (myTta){
                    0 -> see(doCalculate(myC?.eur?.ada?.toFloat(), chosenCurrency));
                    1 -> see(doCalculate(myC?.eur?.aed?.toFloat(), chosenCurrency));
                    2 -> see(doCalculate(myC?.eur?.afn?.toFloat(), chosenCurrency));
                    3 -> see(doCalculate(myC?.eur?.all?.toFloat(), chosenCurrency));
                    4 -> see(doCalculate(myC?.eur?.amd?.toFloat(), chosenCurrency));
                    5 -> see(doCalculate(myC?.eur?.ang?.toFloat(), chosenCurrency));
                    6 -> see(doCalculate(myC?.eur?.aoa?.toFloat(), chosenCurrency));
                    7 -> see(doCalculate(myC?.eur?.ars?.toFloat(), chosenCurrency));
                    8 -> see(doCalculate(myC?.eur?.aud?.toFloat(), chosenCurrency));
                    9 -> see(doCalculate(myC?.eur?.awg?.toFloat(), chosenCurrency));
                    10 -> see(doCalculate(myC?.eur?.azn?.toFloat(), chosenCurrency));
                }
            })
        }
    }
    private fun see (doCalculate: Float){
        val theConvert = findViewById<View>(R.id.forResult) as TextView
        theConvert.text = "result " + doCalculate
    }
    private fun doCalculate(i: Float?, chosenCurrency: Float): Float {
        var ch = 0f
        if (i != null) {
            ch = (i * chosenCurrency)
        }
        return ch
    }
    private fun getCurrency(onResult: (Converence?) -> Unit) {
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

        if (apiInterface != null) {
            apiInterface.getCurrency()?.enqueue(object : Callback<Converence> {
                override fun onResponse(
                    call: Call<Converence>,
                    response: Response<Converence>
                ) {
                    onResult(response.body())

                }

                override fun onFailure(call: Call<Converence>, t: Throwable) {
                    onResult(null)
                    Toast.makeText(applicationContext, "${t.message}", Toast.LENGTH_SHORT).show();
                }

            })
        }
    }
}
