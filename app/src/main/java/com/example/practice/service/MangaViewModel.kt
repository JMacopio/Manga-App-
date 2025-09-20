package com.example.practice.service

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import com.example.practice.MangaDexModels
import kotlinx.coroutines.launch



class MangaViewModel : ViewModel() {
    private val repository = MangaRepository()

    private val _mangaList = MutableLiveData<Result<MangaDexModels>>()
    val mangaList: LiveData<Result<MangaDexModels>> = _mangaList

    private val _selectedManga = MutableLiveData<Result<MangaDexSingleResponse>>()
    val selectedManga: LiveData<Result<MangaDexSingleResponse>> = _selectedManga

    private val _chapters = MutableLiveData<Result<ChapterListResponse>>()
    val chapters: LiveData<Result<MangaDexModels>> = _chapters

    private val _chapterImages = MutableLiveData<Result<AtHomeResponse>>()
    val chapterImages: LiveData<Result<AtHomeResponse>> = _chapterImages

    fun fetchMangaList(limit: Int = 20, offset: Int = 0) {
        viewModelScope.launch {
            _mangaList.value = Result.loading()
            _mangaList.value = repository.getMangaList(limit, offset)
        }
    }

    // Fetch specific manga by ID
    fun fetchMangaById(id: String) {
        viewModelScope.launch {
            _selectedManga.value = Result.loading()
            _selectedManga.value = repository.getMangaById(id)
        }
    }

    // Fetch chapters for a manga
    fun fetchMangaChapters(mangaId: String, limit: Int = 100, offset: Int = 0) {
        viewModelScope.launch {
            _chapters.value = Result.loading()
            _chapters.value = repository.getMangaChapters(mangaId, limit, offset)
        }
    }

    // Fetch chapter images
    fun fetchChapterImages(chapterId: String) {
        viewModelScope.launch {
            _chapterImages.value = Result.loading()
            _chapterImages.value = repository.getChapterImages(chapterId)
        }
    }
}

// Extension for loading state
sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
    object Loading : Result<Nothing>()

    companion object {
        fun <T> loading(): Result<T> = Loading
    }
}

fun <T> Result<T>.isLoading() = this is Result.Loading
fun <T> Result<T>.isSuccess() = this is Result.Success
fun <T> Result<T>.isError() = this is Result.Error