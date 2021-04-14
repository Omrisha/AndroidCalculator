package com.example.calculator.interfaces

import com.example.calculator.models.Variable

interface ICalculator {
    fun calc(number1: Double, number2: Double, operand: String): Double
    fun numberOfVariables() : Int
    fun getVariable(position: Int): Variable
}