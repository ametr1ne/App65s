package com.gmail.filanovsky.app65s

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import android.app.NotificationManager
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlin.math.log

class LoadContactService: Service(), UserRepository {

    private val contactList = ArrayList<Contact.Details>()
    private val contact1 = Contact.Details(0,"Andrey", "+79099994535", email = "example@gmail.com",
        description = "Some things about this person")
    private val contact2 = Contact.Details(1,"Damir", "+790993232434", email = "example2@gmail.com",
        description = "Some things about this person")

    private val _liveData = MutableLiveData<Boolean>()
    val liveData: LiveData<Boolean>
        get() {
            return _liveData
        }

    private val binder = LoadContactBinder()

    override fun onCreate() {
        Thread {
            Thread.sleep(3000)
            contactList.add(contact1)
            contactList.add(contact2)
            _liveData.postValue(true)
        }.start()
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    inner class LoadContactBinder: Binder() {
        fun getService(): LoadContactService = this@LoadContactService
    }

    override fun getCommonContacts(): List<Contact.Common> {
        return contactList.map { Contact.Common(it.id, it.name, it.number) }
    }

    override fun getDetailsContact(): List<Contact.Details> {
        return contactList
    }
}