package com.example.calculator.models

import android.util.Range
import com.example.calculator.enumarations.TypeEnum
import com.example.calculator.interfaces.ICalculator
import java.util.*
import kotlin.collections.ArrayList

class RegularCalculator() : ICalculator {
    private var variables: ArrayList<Variable> = ArrayList()
    init {
        for (i in 1..9) {
            variables.add(Variable(i.toString(), TypeEnum.Operand))
        }
        variables.add(Variable("+", TypeEnum.Operator))
        variables.add(Variable("-", TypeEnum.Operator))
        variables.add(Variable("/", TypeEnum.Operator))
        variables.add(Variable("*", TypeEnum.Operator))
        variables.add(Variable("=", TypeEnum.Operator))
        variables.add(Variable("C", TypeEnum.Operator))
    }
    override fun calc(number1: Double, number2: Double, operand: String): Double {
        return when (operand) {
            "+" -> return number1 + number2
            "-" -> return number1 - number2
            "*" -> return number1 * number2
            "/" -> return number1 / number2
            else -> Math.max(number1, number2)
        }
    }

    override fun numberOfVariables(): Int {
        return variables.size
    }

    override fun getVariable(position: Int): Variable {
        return variables[position]
    }
}