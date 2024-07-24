package yermakov.oleksii.redditnewsfeed.ui.theme.components

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.graphics.drawable.toBitmap
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import kotlinx.coroutines.launch
import yermakov.oleksii.redditnewsfeed.R
import yermakov.oleksii.redditnewsfeed.ui.theme.util.saveImageToGallery

@Composable
fun ImageWithNavigation(
    imageUrl: String,
    modifier: Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    var showFullScreenImage by remember { mutableStateOf(false) }
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }

    if (showFullScreenImage) {
        FullScreenImageDialog(bitmap) {
            showFullScreenImage = false
        }
    }

    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        loading = {
            CircularProgressIndicator(
                color = Color.LightGray,
                modifier = Modifier.padding(48.dp)
            )
        },
        onSuccess = { result ->
            bitmap = (result.result.drawable).toBitmap()
        },
        contentDescription = "Post Image",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .clickable {
                coroutineScope.launch {
                    showFullScreenImage = true
                }
            }
    )
}

@Composable
fun FullScreenImageDialog(bitmap: Bitmap?, onDismiss: () -> Unit) {
    val context = LocalContext.current
    bitmap?.let {
        Dialog(
            onDismissRequest = onDismiss,
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.onBackground
            ) {
                Box {
                    Image(
                        bitmap = it.asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_download_24),
                        contentDescription = "Download Image",
                        modifier = Modifier.fillMaxSize(0.1f)
                            .align(Alignment.TopEnd)
                            .clickable { saveImageToGallery(context, bitmap) }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ImageWithNavigationPreview() {
    ImageWithNavigation(
        imageUrl = "https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png",
        Modifier
    )
}
