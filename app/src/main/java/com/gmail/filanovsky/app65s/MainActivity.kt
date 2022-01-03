package com.gmail.filanovsky.app65s

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log

class MainActivity : AppCompatActivity() {

    private var bound = false
    private lateinit var contactService: LoadContactService

    private val connection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            bound = false
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as LoadContactService.LoadContactBinder
            contactService = binder.getService()
            bound = true
            contactService.liveData.observe(this@MainActivity, { openContactListFragment() })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, LoadContactService::class.java)
        bindService(intent, connection, Context.BIND_AUTO_CREATE)

        setSupportActionBar(findViewById(R.id.toolbar))
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(connection)
        bound = false
    }

    private fun openContactListFragment() {
        val contact1 = contactService.getCommonContacts()[0]
        val contactListFragment = ContactListFragment.getNewInstance(contact1)

        val transaction = supportFragmentManager.beginTransaction()
        transaction
            .add(R.id.fragmentСontainer, contactListFragment)
            .commit()
    }

    fun openContactDetailsFragment(id: Int) {
        val contact = contactService.getDetailsContact()[id]
        val contactDetailsFragment = ContactDetailsFragment.getInstance(contact)

        val transaction = supportFragmentManager.beginTransaction()
        transaction
            .replace(R.id.fragmentСontainer, contactDetailsFragment)
            .addToBackStack(null)
            .commit()
    }
}



