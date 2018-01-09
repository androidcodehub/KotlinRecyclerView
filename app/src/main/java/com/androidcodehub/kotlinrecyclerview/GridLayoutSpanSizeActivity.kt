package com.androidcodehub.kotlinrecyclerview

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

class GridLayoutSpanSizeActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<View>(R.id.recycler_view) as RecyclerView
        recyclerView.addItemDecoration(MarginDecoration(this))
        recyclerView.setHasFixedSize(true)

        val manager = GridLayoutManager(this, 3)
        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return 3 - position % 3
            }
        }
        recyclerView.layoutManager = manager

        recyclerView.adapter = NumberedAdapter(30)
    }
}