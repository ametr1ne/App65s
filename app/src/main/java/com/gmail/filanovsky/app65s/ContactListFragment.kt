package com.gmail.filanovsky.app65s

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.contact_list_item.*

class ContactListFragment : Fragment() {

    companion object {
        fun getNewInstance(contact: Contact.Common): ContactListFragment {
            val contactListFragment = ContactListFragment()
            contactListFragment.arguments = bundleOf("contact" to contact)
            return contactListFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.contact_list_item, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = (arguments?.getSerializable("contact") as Contact.Common).id
        val name = (arguments?.getSerializable("contact") as Contact.Common).name
        val number = (arguments?.getSerializable("contact") as Contact.Common).number

        requireActivity().title = getString(R.string.contact_list)
        nameContactItem.text = name
        phoneNumber.text = number

        view.setOnClickListener {
            onClickListener(id)
        }
    }

    private fun onClickListener(id: Int) {
        (activity as MainActivity).openContactDetailsFragment(id)
    }
}