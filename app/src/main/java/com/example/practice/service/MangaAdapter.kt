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
//    RecyclerView.Adapter<MangaAdapter.MangaViewHolder>() {
//
//    private var mangaList: List<Data> = emptyList()
//
//    inner class MangaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        private val cover: ImageView = itemView.findViewById(R.id.mangaCover)
//        private val title: TextView = itemView.findViewById(R.id.mangaTitle)
//        private val status: TextView = itemView.findViewById(R.id.mangaStatus)
//        private val lastChapter: TextView = itemView.findViewById(R.id.mangaLastChapter)
//
//        fun bind(manga: Data) {
//            // Get the cover image URL from relationships
//            val coverRelationship = manga.relationships.find { it.type == "cover_art" }
//            val coverFileName = coverRelationship?.id
//            val coverUrl = if (coverFileName != null) {
//                "https://uploads.mangadex.org/covers/${manga.id}/${coverFileName}.256.jpg"
//            } else {
//                null
//            }
//
//            // Load cover image with Coil
//            cover.load(coverUrl) {
//                placeholder(R.drawable.placeholder)
//                error(R.drawable.placeholder)
//            }
//
//            // Set title (prefer English title)
//            val englishTitle = manga.attributes.title.en
//            title.text = englishTitle ?: manga.attributes.altTitles.firstOrNull()?.getFirstAvailableTitle() ?: "No Title"
//
//            // Set status
//            status.text = "Status: ${manga.attributes.status.replaceFirstChar { it.uppercase() }}"
//
//            // Set last chapter
//            lastChapter.text = "Last Chapter: ${manga.attributes.lastChapter ?: "N/A"}"
//
//            itemView.setOnClickListener { onItemClick(manga) }
//        }
//    }
//
//    fun updateList(newList: List<Data>) {
//        mangaList = newList
//        notifyDataSetChanged()
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MangaViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_manga, parent, false)
//        return MangaViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: MangaViewHolder, position: Int) {
//        holder.bind(mangaList[position])
//    }
//
//    override fun getItemCount(): Int = mangaList.size
//
//    private fun getCoverImageUrl(manga: Data): String? {
//        // Method 1: Try to get from relationships
//        val coverRelationship = manga.relationships.find { it.type == "cover_art" }
//        val coverArtId = coverRelationship?.id
//
//        if (coverArtId != null) {
//            return "https://uploads.mangadex.org/covers/${manga.id}/$coverArtId.jpg"
//        }
//
//        // Method 2: Try to get fileName from attributes
//        val fileName = coverRelationship?.attributes?.fileName
//        if (fileName != null) {
//            return "https://uploads.mangadex.org/covers/${manga.id}/$fileName.256.jpg"
//        }
//
//        return null
//    }
//}


    RecyclerView.Adapter<MangaAdapter.MangaViewHolder>() {

    private var mangaList: List<Data> = emptyList()

    inner class MangaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cover: ImageView = itemView.findViewById(R.id.mangaCover)
        private val title: TextView = itemView.findViewById(R.id.mangaTitle)
        private val status: TextView = itemView.findViewById(R.id.mangaStatus)
        private val lastChapter: TextView = itemView.findViewById(R.id.mangaLastChapter)

        fun bind(manga: Data) {
            // Get the cover image URL correctly
            val coverUrl = getCoverImageUrl(manga)

            // Load cover image with Coil
            cover.load(coverUrl) {
                placeholder(R.drawable.placeholder)
                error(R.drawable.placeholder)
                crossfade(true)

                listener(
                    onSuccess = { _, _ ->
                        println("Image loaded successfully for ${manga.attributes.title.en}")
                    },
                    onError = { _, result ->
                        println("Image load failed: ${result.throwable.message}")
                    }
                )
            }

            // Set title
            val englishTitle = manga.attributes.title.en
            title.text = englishTitle ?: getFirstAvailableTitle(manga) ?: "No Title"

            // Set status
            status.text = "Status: ${manga.attributes.status.replaceFirstChar { it.uppercase() }}"

            // Set last chapter
            lastChapter.text = "Last Chapter: ${manga.attributes.lastChapter ?: "N/A"}"

            itemView.setOnClickListener { onItemClick(manga) }
        }

        private fun getCoverImageUrl(manga: Data): String? {
            // Find cover art relationship
            val coverRelationship = manga.relationships.find { it.type == "cover_art" }
            val coverFileName = coverRelationship?.attributes?.fileName ?: return null

            // Construct the correct cover URL
            return "https://uploads.mangadex.org/covers/${manga.id}/$coverFileName.256.jpg"
        }

        private fun getFirstAvailableTitle(manga: Data): String? {
            // Try to get any available title from altTitles
            return manga.attributes.altTitles?.flatMap { altTitle ->
                listOfNotNull(
                    altTitle.en,
                    altTitle.ja,
                    altTitle.zh,
                    altTitle.esLa,
                    altTitle.fr,
                    altTitle.ru,
                    altTitle.ptBr
                )
            }?.firstOrNull()
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