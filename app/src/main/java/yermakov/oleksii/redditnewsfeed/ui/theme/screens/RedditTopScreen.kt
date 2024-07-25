package yermakov.oleksii.redditnewsfeed.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import yermakov.oleksii.redditnewsfeed.ui.theme.components.RedditPostCard

@Composable
fun RedditTopScreen(
    modifier: Modifier = Modifier,
    viewModel: RedditTopViewModel = hiltViewModel()
) {
    val listPost by remember { viewModel.displayedPosts }.collectAsState(initial = emptyList())
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.fetchTopRedditPosts()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onBackground)
    ) {
        if (listPost.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
                Spacer(modifier = Modifier.size(32.dp))
            }
        } else {
            Column(
                modifier = Modifier
                    .padding(5.dp)
                    .verticalScroll(scrollState)
            ) {
                listPost.forEach { redditPost ->
                    RedditPostCard(
                        redditPost = redditPost,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp)
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .align(Alignment.BottomCenter)
        ) {
            for (i in 1..5) {
                Button(
                    onClick = {
                        coroutineScope.launch {
                            scrollState.animateScrollTo(0)
                        }
                        viewModel.updateDisplayedPosts(i)
                    },
                    modifier = Modifier.padding(horizontal = 2.dp)
                ) {
                    Text(text = "$i")
                }
            }
        }

    }
}
