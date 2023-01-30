package com.maverick.unittestingexample_cc.mock

class UserRepository {

    private val userList = listOf(
        User(1, "John", "john@gmail.com", "jhvs7vsdv8v"),
        User(2, "Leno", "leno@gmail.com", "jhvs7vsdv8v"),
        User(3, "Tony", "tony@gmail.com", "jhvs7vsdv8v")
    )

    fun login(email: String, password: String): LOGIN_STATUS {
        val user = userList.filter { user -> user.email == email }
        return if (user.size == 1) {
            if (user[0].password == password) {
                LOGIN_STATUS.SUCCESS
            } else {
                LOGIN_STATUS.INVALID_PASSWORD
            }
        } else {
            LOGIN_STATUS.INVALID_USER
        }
    }

}