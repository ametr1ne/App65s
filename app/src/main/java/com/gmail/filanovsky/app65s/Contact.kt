package com.gmail.filanovsky.app65s

import java.io.Serializable

sealed class Contact: Serializable{
    data class Details(
        val id: Int = 0,
        var name: String = "",
        var number: String = "",
        var email: String = "",
        var description: String = "",
        var birthday: String = "01.01.1900") : Contact()

    data class Common(
        val id: Int = 0,
        var name: String = "",
        var number: String = "",
    ) : Contact()
}
