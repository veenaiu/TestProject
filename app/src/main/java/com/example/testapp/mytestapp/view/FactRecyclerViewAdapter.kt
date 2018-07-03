package com.example.testapp.mytestapp.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.testapp.mytestapp.R
import com.example.testapp.mytestapp.domain.Fact
import com.example.testapp.mytestapp.utils.ImageLoader
import kotlinx.android.synthetic.main.fact_list_content.view.*

class FactRecyclerViewAdapter(var facts: MutableList<Fact>) :
        RecyclerView.Adapter<FactRecyclerViewAdapter.ViewHolder>() {

    private val imageLoader = ImageLoader()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fact_list_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fact = facts[position]
        holder.title.text = fact.title
        holder.description.text = fact.description
        holder.image.setImageResource(0)
        imageLoader.loadImage(fact.imageHref, holder.image)

        // For a future details view
        with(holder.itemView) {
            tag = fact
        }
    }

    override fun getItemCount(): Int {
        return facts.size
    }
	//dynamic display in listview
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.title
        val description: TextView = view.description
        val image: ImageView = view.image
    }
}
