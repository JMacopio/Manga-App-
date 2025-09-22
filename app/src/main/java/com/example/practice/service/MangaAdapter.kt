package com.example.practice.service

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.practice.Data
import com.example.practice.R

class MangaAdapter(private val onItemClick: (Data) -> Unit) :
    RecyclerView.Adapter<MangaAdapter.MangaViewHolder>() {

    private var mangaList: List<Data> = emptyList()

    inner class MangaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cover: ImageView = itemView.findViewById(R.id.mangaCover)
        private val title: TextView = itemView.findViewById(R.id.mangaTitle)
        private val status: TextView = itemView.findViewById(R.id.mangaStatus)
        private val lastChapter: TextView = itemView.findViewById(R.id.mangaLastChapter)

        fun bind(manga: Data) {
            // Get the cover image URL from relationships
            val coverRelationship = manga.relationships.find { it.type == "cover_art" }
            val coverFileName = coverRelationship?.id
            val coverUrl = if (coverFileName != null) {
                "https://uploads.mangadex.org/covers/${manga.id}/${coverFileName}.256.jpg"
            } else {
                null
            }

            // Load cover image with Coil
            cover.load(coverUrl) {
                placeholder(R.drawable.placeholder)
                error(R.drawable.placeholder)
            }

            // Set title (prefer English title)
            val englishTitle = manga.attributes.title.en
            title.text = englishTitle ?: manga.attributes.altTitles.firstOrNull()?.getFirstAvailableTitle() ?: "No Title"

            // Set status
            status.text = "Status: ${manga.attributes.status.replaceFirstChar { it.uppercase() }}"

            // Set last chapter
            lastChapter.text = "Last Chapter: ${manga.attributes.lastChapter ?: "N/A"}"

            itemView.setOnClickListener { onItemClick(manga) }
        }
    }

    fun updateList(newList: List<Data>) {
        mangaList = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MangaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_manga, parent, false)
        return MangaViewHolder(view)
    }

    override fun onBindViewHolder(holder: MangaViewHolder, position: Int) {
        holder.bind(mangaList[position])
    }

    override fun getItemCount(): Int = mangaList.size
}