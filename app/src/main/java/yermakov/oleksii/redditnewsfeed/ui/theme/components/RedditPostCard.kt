package yermakov.oleksii.redditnewsfeed.ui.theme.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import yermakov.oleksii.redditnewsfeed.R
import yermakov.oleksii.redditnewsfeed.domain.RedditPost
import yermakov.oleksii.redditnewsfeed.ui.theme.RedditNewsFeedAppTheme
import yermakov.oleksii.redditnewsfeed.ui.theme.util.generateRandomMutedColor

@Composable
fun RedditPostCard(
    redditPost: RedditPost,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .defaultMinSize(),
        colors = CardDefaults.cardColors(
            containerColor = generateRandomMutedColor(),
            contentColor = MaterialTheme.colorScheme.secondary
        )
    ) {
        Column(
            modifier = Modifier.defaultMinSize()
        ) {

            if (redditPost.thumbnail?.endsWith(".jpg") == true) {
                ImageWithNavigation(imageUrl = redditPost.thumbnail, Modifier.fillMaxWidth())
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(16 / 9f),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_video_file_24),
                        contentDescription = "Video",
                        tint = Color.White,
                        modifier = Modifier.size(48.dp)
                    )
                }
            }

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
