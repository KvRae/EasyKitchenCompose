package com.kvrae.easykitchen.utils

fun isValidEmail(email: String): Boolean{
    val emailRegex = Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")
    return emailRegex.matches(email)

}

fun isValidPassword(password: String): Boolean{
    val passwordRegex = Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
    return passwordRegex.matches(password)
}

fun isValidUsername(username: String): Boolean{
    val usernameRegex = Regex("^[a-zA-Z0-9_]{3,20}$")
    return usernameRegex.matches(username)

}

fun isSamePassword(password: String, confirmPassword: String): Boolean{
    return password == confirmPassword
}