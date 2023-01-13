package com.maverick.unittestingexample_cc.utils

class Helper() {

    fun isPalindrome(input: String): Boolean {
        var a = 0
        var b = input.length - 1
        var result = true

        while (a < b) {
            if (input[a] != input[b]) {
                result = false
                break
            }
            a++
            b--
        }
        return result
    }

}