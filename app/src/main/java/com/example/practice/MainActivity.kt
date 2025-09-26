    package com.example.practice

    import android.os.Bundle
    import android.view.View
    import android.widget.ProgressBar
    import android.widget.Toast
    import androidx.activity.enableEdgeToEdge
    import androidx.appcompat.app.AppCompatActivity
    import androidx.core.view.ViewCompat
    import androidx.core.view.WindowInsetsCompat
    import androidx.lifecycle.ViewModelProvider
    import androidx.recyclerview.widget.LinearLayoutManager
    import androidx.recyclerview.widget.RecyclerView
    import com.example.practice.service.MangaAdapter
    import com.example.practice.service.MangaViewModel
    import com.example.practice.service.UIState
    import android.content.Intent

    class MainActivity : AppCompatActivity() {
        private lateinit var viewModel: MangaViewModel
        private lateinit var adapter: MangaAdapter
        private lateinit var recyclerView: RecyclerView
        private lateinit var progressBar: ProgressBar

        override fun onCreate(savedInstanceState: Bundle?) {
    //        super.onCreate(savedInstanceState)
    //        enableEdgeToEdge()
    //        setContentView(R.layout.activity_main)
    //        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
    //            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
    //            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
    //            insets
    //        }
    //
    //        // Initialize ViewModel
    //        viewModel = ViewModelProvider(this)[MangaViewModel::class.java]
    //
    //        // Setup RecyclerView
    //        recyclerView = findViewById(R.id.mangaRecyclerView)
    //        progressBar = findViewById(R.id.progressBar)
    //        adapter = MangaAdapter { manga ->
    //            // Handle manga item click
    //            Toast.makeText(this, "Selected: ${manga.attributes.title.en}", Toast.LENGTH_SHORT).show()
    //            // You can open a detail activity here later
    //        }
    //        recyclerView.adapter = adapter
    //        recyclerView.layoutManager = LinearLayoutManager(this)
    //
    //        // Observe manga list
    //        viewModel.mangaList.observe(this) { state ->
    //            when (state) {
    //                is UIState.Loading -> {
    //                    progressBar.visibility = View.VISIBLE
    //                    recyclerView.visibility = View.GONE
    //                }
    //                is UIState.Success -> {
    //                    progressBar.visibility = View.GONE
    //                    recyclerView.visibility = View.VISIBLE
    //                    adapter.updateList(state.data.data)
    //                }
    //                is UIState.Error -> {
    //                    progressBar.visibility = View.GONE
    //                    Toast.makeText(this, "Error: ${state.message}", Toast.LENGTH_LONG).show()
    //                }
    //            }
    //        }
    //
    //
    //        // Fetch manga list
    //        viewModel.fetchMangaList()

            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContentView(R.layout.activity_main)

            // Fix the edge-to-edge insets - use the correct root view ID
            val rootView = findViewById<View>(R.id.main)
            ViewCompat.setOnApplyWindowInsetsListener(rootView) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }

            setupViews()
            setupViewModel()
            setupRecyclerView()
            observeData()
            loadData()
        }

        private fun setupViews() {
            recyclerView = findViewById(R.id.mangaRecyclerView)
            progressBar = findViewById(R.id.progressBar)
        }

        private fun setupViewModel() {
            viewModel = ViewModelProvider(this)[MangaViewModel::class.java]
        }

        private fun setupRecyclerView() {
            adapter = MangaAdapter { manga ->
                // Navigate to chapter list directly
                val intent = Intent(this, com.example.practice.service.ChapterListActivity::class.java)
                intent.putExtra("MANGA_ID", manga.id)
                startActivity(intent)
            }
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this)
        }

        private fun observeData() {
            viewModel.mangaList.observe(this) { state ->
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

        private fun loadData() {
            viewModel.fetchMangaList()
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