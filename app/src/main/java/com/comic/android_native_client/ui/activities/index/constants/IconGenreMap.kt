package com.comic.android_native_client.ui.activities.index.constants


import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.SentimentSatisfied
import androidx.compose.ui.graphics.vector.ImageVector
import com.comic.android_native_client.R

object GenreIconProvider {
    private lateinit var genreIconMap: Map<String, ImageVector?>

    fun provideGenreIconMap(context: Context): Map<String, ImageVector?> {
        if (!::genreIconMap.isInitialized) {
            genreIconMap = mapOf(
                context.getString(R.string.genre_action) to Icons.Default.PlayArrow,
                context.getString(R.string.genre_adventure) to Icons.Default.PlayArrow,
                context.getString(R.string.genre_anime) to Icons.Default.SentimentSatisfied,
                context.getString(R.string.genre_reincarnation) to Icons.Default.Refresh,
                context.getString(R.string.genre_ancient) to Icons.Default.Flag,
                context.getString(R.string.genre_comedy) to Icons.Default.SentimentSatisfied,
                context.getString(R.string.genre_drama) to Icons.Default.Refresh,
                context.getString(R.string.genre_fantasy) to Icons.Default.Flag,
                context.getString(R.string.genre_historical) to Icons.Default.PlayArrow,
                context.getString(R.string.genre_horror) to Icons.Default.SentimentSatisfied,
                context.getString(R.string.genre_mystery) to Icons.Default.Refresh,
                context.getString(R.string.genre_psychological) to Icons.Default.Flag,
                context.getString(R.string.genre_romance) to Icons.Default.SentimentSatisfied
            )
        }
        return genreIconMap

    }


}
