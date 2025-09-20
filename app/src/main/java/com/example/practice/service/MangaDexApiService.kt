package com.example.practice.service

import com.example.practice.Data
import com.example.practice.MangaDexModels
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
interface MangaDexApiService {

    @GET("manga")
    suspend fun getMangaList(
        @Query("limit") limit: Int = 10,
        @Query("offset") offset: Int = 0,
        @Query("title") title: String? = null,
        @Query("includedTags[]") includedTags: List<String>? = null,
        @Query("excludedTags[]") excludedTags: List<String>? = null,
        @Query("status[]") status: List<String>? = null,
        @Query("order[rating]") orderRating: String? = null,
        @Query("order[followedCount]") orderFollowedCount: String? = null,
        @Query("order[createdAt]") orderCreatedAt: String? = null,
        @Query("order[updatedAt]") orderUpdatedAt: String? = null
    ): Response<MangaDexModels>

    //Get manga details by ID
    @GET("manga/{id}")
    suspend fun getMangaById(@Path("id") id: String): Response<MangaDexSingleResponse>

    //Get chapters for a specific manga
    @GET("manga/{id}/feed")
    suspend fun getMangaChapters(
        @Path("id") id: String,
        @Query("limit") limit: Int = 10,
        @Query("offset") offset: Int = 0,
        @Query("translatedLanguage[]") translatedLanguage: List<String> = listOf("en"),
        @Query("order[readableAt]") orderReadableAt: String = "desc"
    ): Response<MangaDexModels>

    //Get Chapter images (for reading!!!)
    @GET("at-home/server/{id}")
    suspend fun getChapterImages(@Path("id") id: String): Response<AtHomeResponse>

//    //Get specific manga details by ID with relationships
//    @GET("manga/{id}")
//    suspend fun getMangaDetailsById(
//        @Path("id") id: String,
//        @Query("includes[]") includes: List<String> = listOf("author", "artist", "cover_art")
//    ): Response<Data>

//    companion object{
//        const val BASE_URL = "https://api.mangadex.org/"
//
//        fun create(): MangaDexApiService {
//            val retrofit = Retrofit.Builder()
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl(BASE_URL)
//                .build()
//            return retrofit.create(MangaDexApiService::class.java)
//        }
//    }


}