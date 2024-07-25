package yermakov.oleksii.redditnewsfeed

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import yermakov.oleksii.redditnewsfeed.ui.theme.RedditNewsFeedAppTheme
import yermakov.oleksii.redditnewsfeed.ui.theme.screens.RedditTopScreen
import yermakov.oleksii.redditnewsfeed.ui.theme.screens.RedditTopViewModel

@Composable
fun RedditNewsFeedApp(
modifier: Modifier = Modifier
) {
    val viewModel: RedditTopViewModel = hiltViewModel()
    val coroutineScope = rememberCoroutineScope()

    fun refreshData() {
        coroutineScope.launch {
            viewModel.fetchTopRedditPosts()
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
                TopBar(onRefresh = { refreshData()})
            },
        ){innerPadding ->
            RedditTopScreen(modifier = Modifier.padding(innerPadding))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    onRefresh: () -> Unit
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.secondary,
        ),
        title = {
            Text("Reddit Top")
        },
        navigationIcon = {
            IconButton(onClick = { onRefresh() } ){
                Icon(
                    imageVector = Icons.Filled.Refresh,
                    contentDescription = "Retry"
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RedditNewsFeedAppTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            RedditNewsFeedApp()
        }
    }
}
