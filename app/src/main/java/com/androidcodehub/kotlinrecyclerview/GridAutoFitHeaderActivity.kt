package com.androidcodehub.kotlinrecyclerview

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast

class GridAutoFitHeaderActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_fit_recycler_view)
        val recyclerView = findViewById<View>(R.id.recycler_view) as RecyclerView
        recyclerView.addItemDecoration(MarginDecoration(this))
        recyclerView.setHasFixedSize(true)

        val header = LayoutInflater.from(this).inflate(R.layout.auto_fit_header, recyclerView, false)
        header.setOnClickListener { v ->
            Toast.makeText(v.context, R.string.grid_layout_auto_fit_header, Toast.LENGTH_SHORT)
                    .show()
        }
        val adapter = HeaderNumberedAdapter(header, 30)

        val manager = recyclerView.layoutManager as GridLayoutManager
        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (adapter.isHeader(position)) manager.spanCount else 1
            }
        }

        recyclerView.adapter = adapter
    }
}