package com.example.calculator.views

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.calculator.R
import com.example.calculator.interfaces.ICalculator
import com.example.calculator.models.RegularCalculator

class NumPadAdapter(
    private val context: Context,
    private var regularCalculator: ICalculator
) : BaseAdapter() {
    private var layoutInflater: LayoutInflater? = null
    private lateinit var number: TextView

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        if (layoutInflater == null) {
            layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }

        if (convertView == null) {
            convertView = layoutInflater!!.inflate(R.layout.number_item, null)
        }

        number = convertView!!.findViewById(R.id.number_text_view)

        val numberData = regularCalculator.getVariable(position)
        number.text = numberData.value.toString()
        number.textSize = 40F
        number.setTextColor(Color.WHITE)
        number.gravity = Gravity.CENTER

        return convertView
    }

    override fun getItem(p0: Int): Any {
        return regularCalculator.getVariable(p0)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return regularCalculator.numberOfVariables()
    }

}