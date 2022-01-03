package com.gmail.filanovsky.app65s

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.contact_details.*

class ContactDetailsFragment: Fragment() {

    private var contact: Contact.Details? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.contact_details, container, false).also {
            contact = arguments?.get(CONTACT) as Contact.Details
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = getString(R.string.contact_details)

        nameContact.text = contact?.name
        phoneNumber1.text = contact?.number
        email1.text = contact?.email
        description.text = contact?.description
    }

    companion object {
        private const val CONTACT = "contact"

        fun getInstance(contact: Contact.Details): Fragment = ContactDetailsFragment().apply {
            arguments = bundleOf(CONTACT to contact)
        }
    }
}