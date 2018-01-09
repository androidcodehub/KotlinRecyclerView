package com.androidcodehub.kotlinrecyclerview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast

import java.util.ArrayList

class NumberedAdapter(count: Int) : RecyclerView.Adapter<TextViewHolder>() {
    private val labels: MutableList<String>

    init {
        labels = ArrayList(count)
        for (i in 0 until count) {
            labels.add(i.toString())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return TextViewHolder(view)
    }

    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        val label = labels[position]
        holder.textView.text = label
        holder.textView.setOnClickListener {
            Toast.makeText(
                    holder.textView.context, label, Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return labels.size
    }
}