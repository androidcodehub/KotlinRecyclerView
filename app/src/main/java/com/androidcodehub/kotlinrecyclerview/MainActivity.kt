package com.androidcodehub.kotlinrecyclerview


import android.support.v7.app.AppCompatActivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val demos = arrayOf(Test(this, LinearLayoutActivity::class.java, R.string.linear_layout), Test(this, GridLayoutActivity::class.java, R.string.grid_layout), Test(this,
                GridLayoutSpanSizeActivity::class.java, R.string.grid_layout_span_size), Test(this, GridHeaderActivity::class.java, R.string.grid_layout_header), Test(this, GridAutoFitActivity::class.java, R.string.grid_layout_auto_fit), Test(this, GridAutoFitHeaderActivity::class.java, R.string.grid_layout_auto_fit_header), Test(this, GridAddRemoveActivity::class.java, R.string.grid_layout_add_remove))

        val recyclerView = findViewById<View>(R.id.recycler_view) as RecyclerView
        recyclerView.addItemDecoration(MarginDecoration(this))
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = DemoAdapter(demos)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private class DemoAdapter(private val demos: Array<Test>) : RecyclerView.Adapter<TextViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, position: Int): TextViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
            return TextViewHolder(view)
        }

        override fun onBindViewHolder(holder: TextViewHolder, position: Int)

        {
            val demo = demos[position]
            holder.textView.text = demo.title
            holder.textView.setOnClickListener {
                val context = holder.textView.context
                context.startActivity(Intent(context, demo.activityClass))
            }
        }

        override fun getItemCount(): Int {
            return demos.size
        }
    }

    class Test(context: Context, val activityClass: Class<*>, titleId: Int) {
        val title: String

        init {
            this.title = context.getString(titleId)
        }
    }
}
