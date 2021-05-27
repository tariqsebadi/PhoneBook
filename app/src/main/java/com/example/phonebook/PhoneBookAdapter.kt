package com.example.phonebook

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.phonebook.databinding.ListViewItemBinding




class PhoneBookAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<ContactData, PhoneBookAdapter.ContactDataViewHolder>(DiffCallback)  {

    var position:Int = 0
    open class OnClickListener(val clickListener: (contactData:ContactData) -> Unit) {
        fun onClick(contactData:ContactData) = clickListener(contactData)
    }

    class ContactDataViewHolder(private var binding: ListViewItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(contactData:ContactData){
            binding.listItemNumber.text = contactData.number
            binding.listItemName.text = contactData.name
            binding.idlist2.text = position.toString()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactDataViewHolder {
        return ContactDataViewHolder(ListViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ContactDataViewHolder, position: Int) {
        val contactData = getItem(position)
        this@PhoneBookAdapter.position = position
        holder.itemView.setOnClickListener { onClickListener.onClick(contactData) }
        holder.bind(contactData)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<ContactData>(){
        override fun areItemsTheSame(oldItem: ContactData, newItem: ContactData): Boolean {
            return oldItem === newItem//oldItem.id == newItem.id are items same?
        }

        override fun areContentsTheSame(oldItem: ContactData, newItem: ContactData): Boolean {
            return oldItem == newItem // are contents same?
        }
    }


}

