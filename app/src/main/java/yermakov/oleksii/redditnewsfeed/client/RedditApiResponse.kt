package yermakov.oleksii.redditnewsfeed.client

import com.google.gson.annotations.SerializedName
import yermakov.oleksii.redditnewsfeed.domain.RedditPost

data class RedditApiResponse(
    @SerializedName("data") val listingData: ListingData,
    val after: String?
)

data class ListingData(
    @SerializedName("children") val children: List<Child>,
    val after: String?
)

data class Child(
    @SerializedName("data") val data: RedditPost
)