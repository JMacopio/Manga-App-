package com.example.practice.service

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import com.example.practice.MangaDexModels
import kotlinx.coroutines.launch


class MangaViewModel : ViewModel() {
//    private val repository = MangaRepository()
//
//    private val _mangaList = MutableLiveData<Result<MangaDexModels>>()
//    val mangaList: LiveData<Result<MangaDexModels>> = _mangaList
//
//    private val _selectedManga = MutableLiveData<Result<MangaDexSingleResponse>>()
//    val selectedManga: LiveData<Result<MangaDexSingleResponse>> = _selectedManga
//
//    private val _chapters = MutableLiveData<Result<ChapterListResponse>>()
//    val chapters: LiveData<Result<ChapterListResponse>> = _chapters
//
//    private val _chapterImages = MutableLiveData<Result<AtHomeResponse>>()
//    val chapterImages: LiveData<Result<AtHomeResponse>> = _chapterImages
//
//    fun fetchMangaList(limit: Int = 10, offset: Int = 0) {
//        viewModelScope.launch {
//            _mangaList.value = Result.loading()
//            _mangaList.value = repository.getMangaList(limit, offset)
//        }
//    }

    // Fetch specific manga by ID
//    fun fetchMangaById(id: String) {
//        viewModelScope.launch {
//            _selectedManga.value = Result.loading()
//            _selectedManga.value = repository.getMangaById(id)
//        }
//    }

    // Fetch chapters for a manga
//    fun fetchMangaChapters(mangaId: String, limit: Int = 100, offset: Int = 0) {
//        viewModelScope.launch {
//            _chapters.value = Result.loading()
//            _chapters.value = repository.getMangaChapters(mangaId, limit, offset)
//        }
//    }

    // Fetch chapter images
//    fun fetchChapterImages(chapterId: String) {
//        viewModelScope.launch {
//            _chapterImages.value = Result.loading()
//            _chapterImages.value = repository.getChapterImages(chapterId)
//        }
//    }

    private val repository = MangaRepository()

    private val _mangaList = MutableLiveData<UIState<MangaDexModels>>()
    val mangaList: LiveData<UIState<MangaDexModels>> = _mangaList

    private val _selectedManga = MutableLiveData<UIState<MangaDexSingleResponse>>()
    val selectedManga: LiveData<UIState<MangaDexSingleResponse>> = _selectedManga

    private val _chapters = MutableLiveData<UIState<ChapterListResponse>>()
    val chapters: LiveData<UIState<ChapterListResponse>> = _chapters

    private val _chapterImages = MutableLiveData<UIState<AtHomeResponse>>()
    val chapterImages: LiveData<UIState<AtHomeResponse>> = _chapterImages

    fun fetchMangaList(limit: Int = 20, offset: Int = 0) {
        viewModelScope.launch {
            _mangaList.value = UIState.Loading
            val result = repository.getMangaList(limit, offset)
            _mangaList.value = result.toUIState()
        }
    }

    // Fetch specific manga by ID
    fun fetchMangaById(id: String) {
        viewModelScope.launch {
            _selectedManga.value = UIState.Loading
            val result = repository.getMangaById(id)
            _selectedManga.value = result.toUIState()
        }
    }

    // Fetch chapters for a manga
    fun fetchMangaChapters(mangaId: String, limit: Int = 100, offset: Int = 0) {
        viewModelScope.launch {
            _chapters.value = UIState.Loading
            val result = repository.getMangaChapters(mangaId, limit, offset)
            _chapters.value = result.toUIState()
        }
    }

    // Fetch chapter images
    fun fetchChapterImages(chapterId: String) {
        viewModelScope.launch {
            _chapterImages.value = UIState.Loading
            val result = repository.getChapterImages(chapterId)
            _chapterImages.value = result.toUIState()
        }
    }

    // Extension function to convert Kotlin Result to UIState
    private fun <T> Result<T>.toUIState(): UIState<T> {
        return fold(
            onSuccess = { UIState.Success(it) },
            onFailure = { UIState.Error(it.message ?: "Unknown error") }
        )
    }
}

// UI State for handling different states in the UI
sealed class UIState<out T> {
    object Loading : UIState<Nothing>()
    data class Success<out T>(val data: T) : UIState<T>()
    data class Error(val message: String) : UIState<Nothing>()
}

// Extension functions for easy checking
fun <T> UIState<T>.isLoading() = this is UIState.Loading
fun <T> UIState<T>.isSuccess() = this is UIState.Success
fun <T> UIState<T>.isError() = this is UIState.Error



// Extension for loading state
//sealed class Result<out T> {
//    data class Success<out T>(val data: T) : Result<T>()
//    data class Error(val exception: Exception) : Result<Nothing>()
//    object Loading : Result<Nothing>()
//
//    companion object {
//        fun <T> loading(): Result<T> = Loading
//    }
//}
//
//fun <T> Result<T>.isLoading() = this is Result.Loading
//fun <T> Result<T>.isSuccess() = this is Result.Success
//fun <T> Result<T>.isError() = this is Result.Error