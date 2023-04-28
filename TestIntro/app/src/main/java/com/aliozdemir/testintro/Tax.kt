package com.aliozdemir.testintro

class Tax {

    // TDD - Test Driven Development

    // Test - Development - Test - Development - Test

    fun calculateTax(grossIncome: Double, taxRate: Double) : Double {
        return grossIncome * taxRate
    }

    fun calculateIncome(grossIncome: Double, taxRate: Double) : Double {
        return grossIncome - (grossIncome * taxRate)
    }

}