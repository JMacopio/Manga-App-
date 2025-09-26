package com.example.practice.service

import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
//import androidx.databinding.DataBindingUtil.setContentView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practice.R
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class ChapterReaderActivity : AppCompatActivity() {

    private lateinit var viewModel: MangaViewModel
    private lateinit var chapterId: String
    private lateinit var adapter: ChapterPageAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chapter_reader)

        // Get chapter ID from intent
        chapterId = intent.getStringExtra("CHAPTER_ID") ?: ""
        if (chapterId.isEmpty()) {
            Toast.makeText(this, "Invalid chapter ID", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        initializeViews()
        setupRecyclerView()
        setupViewModel()
        observeChapterImages()
        loadChapterImages()
    }

    private fun initializeViews() {
        progressBar = findViewById(R.id.progressBar)
        recyclerView = findViewById(R.id.chapterPagesRecyclerView)
    }

    private fun setupRecyclerView() {
        adapter = ChapterPageAdapter(emptyList())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this)[MangaViewModel::class.java]
    }

    private fun observeChapterImages() {
        viewModel.chapterImages.observe(this) { state ->
            when (state) {
                is UIState.Loading -> {
                    showLoading()
                }
                is UIState.Success -> {
                    hideLoading()
                    val imageUrls = state.data.chapter.data.map { page ->
                        "${state.data.baseUrl}/data/${state.data.chapter.hash}/$page"
                    }
                    adapter.updatePages(imageUrls)
                }
                is UIState.Error -> {
                    hideLoading()
                    showError(state.message)
                }
            }
        }
    }

    private fun loadChapterImages() {
        viewModel.fetchChapterImages(chapterId)
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