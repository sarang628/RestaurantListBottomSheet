package com.sarang.torang

interface SelectRestaurantUseCase {
    suspend fun invoke(restaurantId : Int)
}