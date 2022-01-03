package com.gmail.filanovsky.app65s

interface UserRepository {
    fun getCommonContacts(): List<Contact.Common>

    fun getDetailsContact(): List<Contact.Details>
}