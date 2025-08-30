package com.sarang.torang

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

typealias RestaurantItemImageLoader = @Composable (modifier: Modifier, url: String, width: Dp?, height: Dp?, contentScale: ContentScale?) -> Unit

val LocalRestaurantItemImageLoader = compositionLocalOf<RestaurantItemImageLoader> {
    // 기본 구현: 경고 로그 출력
    @Composable { modifier, _, _, _, _ ->
        Image(modifier = Modifier.size(120.dp).clip(RoundedCornerShape(16.dp)), painter = painterResource(R.drawable.salad), contentDescription = "", contentScale = ContentScale.Crop)
    }
}