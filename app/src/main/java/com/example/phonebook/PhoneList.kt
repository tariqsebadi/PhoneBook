package com.example.phonebook

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.phonebook.databinding.FragmentPhoneListBinding



/**
 * A simple [Fragment] subclass.
 * Use the [PhoneList.newInstance] factory method to
 * create an instance of this fragment.
 */
class PhoneList : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    val data: MutableList<ContactData> = mutableListOf()
    lateinit var binding:FragmentPhoneListBinding
    lateinit var myAdapter: PhoneBookAdapter
    var contactId = 0
    var position = 0
    lateinit var args: PhoneListArgs

    private val viewModel: PhoneBookViewModel by lazy { ViewModelProvider(this).get(PhoneBookViewModel::class.java) }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<FragmentPhoneListBinding>(inflater, R.layout.fragment_phone_list, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        myAdapter = PhoneBookAdapter(PhoneBookAdapter.OnClickListener{
            view?.findNavController()?.navigate(PhoneListDirections.actionPhoneListToEditContactFragment(
                data[position].name,
                data[position].number,
                data[position].id.toString()
            ))
        })
//        binding.textName.toString(),
//        binding.textNumber.toString()
        binding.recyclerViewContactlist.adapter = myAdapter
        binding.recyclerViewContactlist.hasFixedSize()


        for(i in 1..1){
            data.add(ContactData(contactId++,"name $i", "number $i"))
        }

        myAdapter.submitList(data)

//        binding.recyclerViewContactlist.addOnItemTouchListener()
//        viewModel.contact.observe(viewLifecycleOwner, Observer {
//            it.let { adapter.submitList(it) }
//        })
        binding.addContactButton.setOnClickListener {

            activity?.let { it1 -> hideSoftKeyboard(it1) }
            Add()
        }

        //args = PhoneListArgs.fromBundle(requireArguments())
        //Toast.makeText(context, "Name: ${args.newName}, Number: ${args.newNumber}", Toast.LENGTH_LONG).show()

        return binding.root
    }

    fun Add(){
        data.add(ContactData(contactId++, binding.textName.text.toString(),binding.textNumber.text.toString()))
        var clone:MutableList<ContactData> = mutableListOf()
        clone.addAll(data)
        myAdapter.submitList(clone)

    }

    fun hideSoftKeyboard(activity: Activity) {
        if (activity.getCurrentFocus() == null){
            return
        }
        val inputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus()!!.getWindowToken(), 0)
    }


}