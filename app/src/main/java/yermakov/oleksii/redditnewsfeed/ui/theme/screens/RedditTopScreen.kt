package yermakov.oleksii.redditnewsfeed.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import yermakov.oleksii.redditnewsfeed.ui.theme.components.RedditPostCard

@Composable
fun RedditTopScreen(
    modifier: Modifier = Modifier,
    viewModel: RedditTopViewModel = hiltViewModel()
) {
    val listPost = viewModel.listProduct.collectAsState().value
    viewModel.fetchTopRedditPosts()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onBackground)
    ) {
        if (listPost.isEmpty()) {
            Column {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.size(32.dp))
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(1),
                modifier = Modifier.padding(5.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp),
                content = {
                items(listPost.size) { index ->
                    RedditPostCard(
                        redditPost = listPost[index], modifier = Modifier.fillMaxWidth()
                    )
                }
            })
        }

    }
}