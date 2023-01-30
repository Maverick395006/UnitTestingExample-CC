package com.maverick.unittestingexample_cc.mock

class UserService(private val userRepository: UserRepository) {
    fun loginUser(email: String, password: String): String {
        val status = userRepository.login(email, password)
        return when (status) {
            LOGIN_STATUS.INVALID_USER -> "User does not exist"
            LOGIN_STATUS.INVALID_PASSWORD -> "Invalid password"
            LOGIN_STATUS.INVALID_ERROR -> "Unknown error occured"
            LOGIN_STATUS.SUCCESS -> "Logged in successfully"
        }
    }
}