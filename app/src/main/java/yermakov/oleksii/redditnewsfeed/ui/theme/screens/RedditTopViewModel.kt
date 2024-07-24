package yermakov.oleksii.redditnewsfeed.ui.theme.screens;

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import yermakov.oleksii.redditnewsfeed.domain.RedditPost
import yermakov.oleksii.redditnewsfeed.domain.dummyRedditPostList
import javax.inject.Inject

@HiltViewModel
class RedditTopViewModel @Inject constructor() : ViewModel() {
 private val _listPost : MutableStateFlow<MutableList<RedditPost>> = MutableStateFlow(mutableListOf())
 val listProduct = _listPost.asStateFlow()

 fun fetchTopRedditPosts() = viewModelScope.launch{
     _listPost.emit(dummyRedditPostList().toMutableList())
 }
}
