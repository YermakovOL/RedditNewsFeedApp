package yermakov.oleksii.redditnewsfeed.client

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditApi {
    @GET("top.json")
    suspend fun getTopPosts(
        @Query("limit") limit: Int,
        @Query("after") after: String? = null
    ): RedditApiResponse
}

