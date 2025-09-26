package com.example.practice.service

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practice.R

class ChapterListActivity : AppCompatActivity() {
    private lateinit var viewModel: MangaViewModel
    private lateinit var mangaId: String
    private lateinit var adapter: ChapterAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chapter_list)

        // Get manga ID from intent
        mangaId = intent.getStringExtra("MANGA_ID") ?: ""
        if (mangaId.isEmpty()) {
            Toast.makeText(this, "Invalid manga ID", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        initializeViews()
        setupRecyclerView()
        setupViewModel()
        observeChapters()
        loadChapters()
    }

    private fun initializeViews() {
        progressBar = findViewById(R.id.progressBar)
        recyclerView = findViewById(R.id.chaptersRecyclerView)
    }

    private fun setupRecyclerView() {
        adapter = ChapterAdapter { chapter ->
            // Open chapter reader
            val intent = Intent(this, ChapterReaderActivity::class.java)
            intent.putExtra("CHAPTER_ID", chapter.id)
            startActivity(intent)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this)[MangaViewModel::class.java]
    }

    private fun observeChapters() {
        viewModel.chapters.observe(this) { state ->
            when (state) {
                is UIState.Loading -> {
                    showLoading()
                }
                is UIState.Success -> {
                    hideLoading()
                    adapter.updateList(state.data.data)
                }
                is UIState.Error -> {
                    hideLoading()
                    showError(state.message)
                }
            }
        }
    }

    private fun loadChapters() {
        viewModel.fetchMangaChapters(mangaId)
    }

    private fun showLoading() {
        progressBar.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
    }

    private fun hideLoading() {
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
    }

    private fun showError(message: String) {
        Toast.makeText(this, "Error: $message", Toast.LENGTH_LONG).show()
    }
}