package com.sarang.torang

import com.sarang.torang.di.image.provideTorangAsyncImage

val CustomRestaurantItemImageLoader : RestaurantItemImageLoader get() = provideTorangAsyncImage()