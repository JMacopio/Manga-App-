package com.example.practice.service

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practice.R

class ChapterAdapter (private val onItemClick: (ChapterData) -> Unit) :
    RecyclerView.Adapter<ChapterAdapter.ChapterViewHolder>() {

        private var chapterList: List<ChapterData> = emptyList()

        inner class ChapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val chapterNumber: TextView = itemView.findViewById(R.id.chapterNumber)
            private val chapterTitle: TextView = itemView.findViewById(R.id.chapterTitle)
            private val chapterPages: TextView = itemView.findViewById(R.id.chapterPages)

            fun bind(chapter: ChapterData) {
                chapterNumber.text = "Chapter ${chapter.attributes.chapter ?: "N/A"}"
                chapterTitle.text = chapter.attributes.title ?: "No Title"
                chapterPages.text = "${chapter.attributes.pages} pages"

                itemView.setOnClickListener {
                    onItemClick(chapter)
                }
            }
        }

        fun updateList(newList: List<ChapterData>) {
            chapterList = newList
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapterViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chapter, parent, false)
            return ChapterViewHolder(view)
        }

        override fun onBindViewHolder(holder: ChapterViewHolder, position: Int) {
            holder.bind(chapterList[position])
        }

        override fun getItemCount(): Int = chapterList.size
}