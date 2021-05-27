package com.example.phonebook

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.phonebook.databinding.FragmentEditContactBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EditContactFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditContactFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentEditContactBinding>(
            inflater, R.layout.fragment_edit_contact,container,false)

        var args = EditContactFragmentArgs.fromBundle(requireArguments())
        Toast.makeText(context, "Name: ${args.name}, Number: ${args.number}", Toast.LENGTH_LONG).show()

        binding.button.setOnClickListener {
            view?.findNavController()?.navigate(EditContactFragmentDirections.actionEditContactFragmentToPhoneList(
                "newName","newNumber","0"
            ))
        }
        return binding.root
    }

}