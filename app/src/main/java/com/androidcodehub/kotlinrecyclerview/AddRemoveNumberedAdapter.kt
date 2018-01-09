package com.androidcodehub.kotlinrecyclerview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

import java.util.ArrayList

class AddRemoveNumberedAdapter(count: Int) : RecyclerView.Adapter<TextViewHolder>() {

    private val labels: MutableList<String>

    init {
        labels = ArrayList(count)
        for (i in 0 until count) {
            labels.add(i.toString())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
                if (viewType == ITEM_VIEW_TYPE_ADD) R.layout.item_add else R.layout.item, parent, false)
        return TextViewHolder(view)
    }

    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        if (position == labels.size) {
            holder.textView.setOnClickListener { addItem() }
            return
        }

        val label = labels[position]
        holder.textView.text = label
        holder.textView.setOnClickListener { removeItem(holder.position) }
    }

    private fun addItem() = if (labels.size >= 1) {
        val lastItem = Integer.parseInt(labels[labels.size - 1])
        labels.add((lastItem + 1).toString())
        notifyItemInserted(labels.size - 1)
    } else {
        labels.add("0")
        notifyItemInserted(0)
    }

    private fun removeItem(position: Int) {
        labels.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == labels.size) ITEM_VIEW_TYPE_ADD else ITEM_VIEW_TYPE_ITEM
    }

    override fun getItemCount(): Int {
        return labels.size + 1
    }

    companion object {
        private val ITEM_VIEW_TYPE_ITEM = 0
        private val ITEM_VIEW_TYPE_ADD = 1
    }
}