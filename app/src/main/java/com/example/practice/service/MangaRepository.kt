package com.example.practice.service

import android.util.Log
import com.example.practice.MangaDexModels
import com.example.practice.service.RetrofitInstance.api

class MangaRepository {
    private val api = RetrofitInstance.api

    // Existing method for manga list
    suspend fun getMangaList(limit: Int = 10, offset: Int = 0): Result<MangaDexModels> {
        return try {
            val response = api.getMangaList(limit, offset)
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Failed to fetch manga: ${response.code()}"))
            }
        } catch (e: Exception) {
            Log.e("MangaRepository", "Error fetching manga list", e)
            Result.failure(e)
            }
        }
    }


// Get specific manga by ID
    suspend fun getMangaById(id: String): Result<MangaDexSingleResponse> {
        return try {
            val response = api.getMangaById(id)
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Failed to fetch manga: ${response.code()}"))
            }
        } catch (e: Exception) {
            Log.e("MangaRepository", "Error fetching manga by ID", e)
            Result.failure(e)
        }
    }

    // Get chapters for a manga
    suspend fun getMangaChapters(
        mangaId: String,
        limit: Int = 100,
        offset: Int = 0
    ): Result<MangaDexModels> {
        return try {
            val response = api.getMangaChapters(mangaId, limit, offset)
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Failed to fetch chapters: ${response.code()}"))
            }
        } catch (e: Exception) {
            Log.e("MangaRepository", "Error fetching manga chapters", e)
            Result.failure(e)
        }
    }

    // Get chapter images
    suspend fun getChapterImages(chapterId: String): Result<AtHomeResponse> {
        return try {
            val response = api.getChapterImages(chapterId)
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Failed to fetch chapter images: ${response.code()}"))
            }
        } catch (e: Exception) {
            Log.e("MangaRepository", "Error fetching chapter images", e)
            Result.failure(e)
        }
    }
