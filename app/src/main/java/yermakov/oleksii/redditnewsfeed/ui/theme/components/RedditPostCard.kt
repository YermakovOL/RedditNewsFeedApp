package yermakov.oleksii.redditnewsfeed.ui.theme.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import yermakov.oleksii.redditnewsfeed.R
import yermakov.oleksii.redditnewsfeed.domain.RedditPost
import yermakov.oleksii.redditnewsfeed.ui.theme.RedditNewsFeedAppTheme
import yermakov.oleksii.redditnewsfeed.ui.theme.Shapes

@Composable
fun RedditPostCard(
    redditPost: RedditPost,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .padding(8.dp)
            .defaultMinSize(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.secondary
        )
    ) {
        Column(
            modifier = Modifier.defaultMinSize()
        ) {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(redditPost.thumbnail)
                    .crossfade(true)
                    .build(),
                loading = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            color = Color.LightGray,
                            modifier = Modifier.padding(48.dp)
                        )
                    }
                },
                contentDescription = "Post Image",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(180.dp)
                    .clip(Shapes.medium)
            )
            HorizontalDivider(thickness = 1.dp)
            Column(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = redditPost.postName,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.AccountCircle,
                            contentDescription = "Author",
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.scale(0.8f)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = redditPost.postAuthor,
                            style = MaterialTheme.typography.titleMedium,
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_mode_comment_24),
                            contentDescription = "Comments",
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.scale(0.8f)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = redditPost.countOfComments.toString(),
                            style = MaterialTheme.typography.titleMedium,
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductItemPreview() {
    RedditNewsFeedAppTheme {
        RedditPostCard(
            redditPost = RedditPost(
                postAuthor = "Author",
                postName = "Reddit Post Title",
                countOfComments = 123,
                thumbnail = "https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png"
            ),
            modifier = Modifier
        )
    }
}
