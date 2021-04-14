package com.example.calculator.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.TextView
import com.example.calculator.R
import com.example.calculator.interfaces.ICalculator
import com.example.calculator.models.RegularCalculator
import com.example.calculator.models.Variable

/**
 * A simple [Fragment] subclass.
 * Use the [NumPadFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NumPadFragment : Fragment() {
    private lateinit var numberPadAdapter: NumPadAdapter
    private var regularCalculator: ICalculator = RegularCalculator()
    private var scientificCalculator: ICalculator = RegularCalculator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_num_pad, container, false)

        val resultTextView = view.findViewById<TextView>(R.id.result_screen_text_view)
        val memoryTextView = view.findViewById<TextView>(R.id.memory_result_view)

        val gridView = view.findViewById<GridView>(R.id.num_pad_grid)
        numberPadAdapter = NumPadAdapter(view.context, regularCalculator)
        gridView.adapter = numberPadAdapter
        gridView.numColumns = 3
        gridView.setOnItemClickListener { _, _, i, _ ->
            val number = regularCalculator.getVariable(i)
            when (number.value) {
                "+", "-", "*", "/" -> {
                    val num = resultTextView.text.toString().toDouble()
                    val memory = memoryTextView.text.toString().toDouble()
                    memoryTextView.text = regularCalculator.calc(memory, num, number.value).toString()
                    resultTextView.text = "0.0"
                }
                "C" -> {
                    resultTextView.text = "0.0"
                    memoryTextView.text = "0.0"
                }
                "=" -> {
                    val value = resultTextView.text.toString().toDouble()
                    val memory = memoryTextView.text.toString().toDouble()
                    memoryTextView.text = regularCalculator.calc(memory, value, "+").toString()
                    resultTextView.text = "0.0"
                }
                else -> {
                    val num = resultTextView.text.toString().toDouble()
                    if (num > 0.0) {
                        resultTextView.append(number.value)
                    } else {
                        resultTextView.text = number.value
                    }
                }
            }
        }
        return view
    }
}