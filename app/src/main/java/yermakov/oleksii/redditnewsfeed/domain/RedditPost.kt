package yermakov.oleksii.redditnewsfeed.domain

import androidx.lifecycle.ViewModel

data class RedditPost(
    val postName: String,
    val postAuthor: String,
    val countOfComments: Int,
    val thumbnail: String?
)