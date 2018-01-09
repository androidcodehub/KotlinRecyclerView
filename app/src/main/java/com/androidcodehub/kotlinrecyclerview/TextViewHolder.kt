package com.androidcodehub.kotlinrecyclerview

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

class TextViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var textView: TextView

    init {
        textView = itemView?.findViewById<View>(R.id.text) as TextView
    }
}