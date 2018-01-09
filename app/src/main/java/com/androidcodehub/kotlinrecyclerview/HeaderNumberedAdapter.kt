package com.androidcodehub.kotlinrecyclerview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import java.util.ArrayList

class HeaderNumberedAdapter(private val header: View?, count: Int) : RecyclerView.Adapter<TextViewHolder>() {
    private val labels: MutableList<String>

    init {
        if (header == null) {
            throw IllegalArgumentException("header may not be null")
        }
        this.labels = ArrayList(count)
        for (i in 0 until count) {
            labels.add(i.toString())
        }
    }

    fun isHeader(position: Int): Boolean {
        return position == 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
        if (viewType == ITEM_VIEW_TYPE_HEADER) {
            return TextViewHolder(header)
        }
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return TextViewHolder(view)
    }

    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        if (isHeader(position)) {
            return
        }
        val label = labels[position - 1]  // Subtract 1 for header
        holder.textView.text = label
        holder.textView.setOnClickListener {
            Toast.makeText(
                    holder.textView.context, label, Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (isHeader(position)) ITEM_VIEW_TYPE_HEADER else ITEM_VIEW_TYPE_ITEM
    }

    override fun getItemCount(): Int {
        return labels.size + 1
    }

    companion object {
        private val ITEM_VIEW_TYPE_HEADER = 0
        private val ITEM_VIEW_TYPE_ITEM = 1
    }
}