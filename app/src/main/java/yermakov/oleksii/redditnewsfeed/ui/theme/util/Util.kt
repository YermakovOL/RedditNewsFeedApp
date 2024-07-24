package yermakov.oleksii.redditnewsfeed.ui.theme.util

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.compose.ui.graphics.Color
import java.io.OutputStream
import kotlin.random.Random

fun saveImageToGallery(context: Context, bitmap: Bitmap) {
    val filename = "${System.currentTimeMillis()}.jpg"
    val contentValues = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
        put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
        put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
    }

    val resolver = context.contentResolver
    var outputStream: OutputStream? = null

    try {
        val imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
        if (imageUri != null) {
            outputStream = resolver.openOutputStream(imageUri)
            if (outputStream != null) {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                Toast.makeText(context, "Image saved to gallery", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Failed to save image", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "Failed to create new MediaStore record", Toast.LENGTH_SHORT).show()
        }
    } catch (e: Exception) {
        e.printStackTrace()
        Toast.makeText(context, "Error saving image: ${e.message}", Toast.LENGTH_SHORT).show()
    } finally {
        outputStream?.close()
    }
}

fun generateRandomMutedColor(): Color {
    val random = Random
    val red = random.nextInt(128) + 128
    val green = random.nextInt(128) + 128
    val blue = random.nextInt(128) + 128
    return Color(red, green, blue)
}
