package com.gmail.filanovsky.app65s

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class ContactListFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.contact_list_item, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().title = getString(R.string.contact_list)

        view.setOnClickListener {
            onClickListener()
        }
    }

    private fun onClickListener() {
        val transaction = parentFragmentManager.beginTransaction()
        transaction
            .replace(R.id.fragmentСontainer, ContactDetailsFragment())
            .addToBackStack(null)
            .commit()
    }
}