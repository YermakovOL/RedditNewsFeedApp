package yermakov.oleksii.redditnewsfeed.ui.theme.screens;

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import yermakov.oleksii.redditnewsfeed.dao.RedditDao
import yermakov.oleksii.redditnewsfeed.domain.RedditPost
import javax.inject.Inject

@HiltViewModel
class RedditTopViewModel @Inject constructor(private val redditDao: RedditDao) : ViewModel() {
    private val allPosts: MutableList<RedditPost> = mutableListOf()
    private val _displayedPosts: MutableStateFlow<List<RedditPost>> = MutableStateFlow(emptyList())

    val displayedPosts = _displayedPosts.asStateFlow()

    fun fetchTopRedditPosts() = viewModelScope.launch {
        val posts = redditDao.fetchTopPosts()
        posts?.let {
            allPosts.clear()
            allPosts.addAll(it)
            updateDisplayedPosts(1)
        }
    }

    fun updateDisplayedPosts(page: Int, pageSize: Int = 20) {
        val start = (page - 1) * pageSize
        val end = minOf(start + pageSize, allPosts.size)
        if (start < end) {
            val pagedPosts = allPosts.slice(start until end)
            _displayedPosts.value = pagedPosts
        }
    }
}
