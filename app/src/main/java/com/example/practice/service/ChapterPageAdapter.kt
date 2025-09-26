package com.example.practice.service

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.practice.R

class ChapterPageAdapter (private var pages: List<String>) :
    RecyclerView.Adapter<ChapterPageAdapter.PageViewHolder>() {

    inner class PageViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_chapter_page, parent, false)
    ) {
        private val pageImage: ImageView = itemView.findViewById(R.id.pageImage)

        fun bind(imageUrl: String) {
            pageImage.load(imageUrl) {
                placeholder(R.drawable.placeholder)
                error(R.drawable.placeholder)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        return PageViewHolder(parent)
    }

    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        holder.bind(pages[position])
    }

    override fun getItemCount(): Int = pages.size

    fun updatePages(newPages: List<String>) {
        pages = newPages
        notifyDataSetChanged()
    }
}