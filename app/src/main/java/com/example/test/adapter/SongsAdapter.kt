package com.example.test.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList
import com.example.test.databinding.RecylerviewItemBinding
import com.example.test.models.Songs


class SongsAdapter(
    var list: ArrayList<Songs>
) : RecyclerView.Adapter<SongsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            RecylerviewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(
            list[position]
        )
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(private var item: RecylerviewItemBinding) : RecyclerView.ViewHolder(item.root) {
        fun bind(person: Songs) {
            item.name.text = person.songName.trim()
            item.age.text = person.time.toString().trim()
        }
    }
}