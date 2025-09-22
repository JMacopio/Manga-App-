package com.example.practice.service

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.practice.Data
import com.example.practice.R
import com.example.practice.databinding.ActivityMangaDetailBinding

class MangaDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMangaDetailBinding
    private lateinit var viewModel: MangaViewModel
    private lateinit var mangaId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMangaDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get manga ID from intent
        mangaId = intent.getStringExtra("MANGA_ID") ?: ""
        if (mangaId.isEmpty()) {
            Toast.makeText(this, "Invalid manga ID", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // Initialize ViewModel
        viewModel = ViewModelProvider(this)[MangaViewModel::class.java]

        // Observe manga details
        viewModel.selectedManga.observe(this) { result ->
            when {
                result.isLoading() -> {
                    binding.progressBar.visibility = android.view.View.VISIBLE
                }
                result.isSuccess() -> {
                    binding.progressBar.visibility = android.view.View.GONE
                    val manga = (result as Result.Success).data.data
                    displayMangaDetails(manga)
                }
                result.isError() -> {
                    binding.progressBar.visibility = android.view.View.GONE
                    val error = (result as Result.Error).exception
                    Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_LONG).show()
                }
            }
        }

        // Fetch manga details
        viewModel.fetchMangaById(mangaId)
    }

    private fun displayMangaDetails(manga: Data) {
        // Load cover image
        val coverRelationship = manga.relationships.find { it.type == "cover_art" }
        val coverFileName = coverRelationship?.id
        val coverUrl = if (coverFileName != null) {
            "https://uploads.mangadex.org/covers/${manga.id}/${coverFileName}.512.jpg"
        } else {
            null
        }
        binding.mangaCover.load(coverUrl) {
            placeholder(R.drawable.placeholder)
            error(R.drawable.placeholder)
        }

        // Set title
        binding.mangaTitle.text = manga.attributes.title.en

        // Set description
        binding.mangaDescription.text = manga.attributes.description?.en ?: "No description available"

        // Set status and other details
        binding.mangaStatus.text = "Status: ${manga.attributes.status.replaceFirstChar { it.uppercase() }}"
        binding.mangaLastChapter.text = "Last Chapter: ${manga.attributes.lastChapter ?: "N/A"}"

        // You can add more details as needed
    }
}