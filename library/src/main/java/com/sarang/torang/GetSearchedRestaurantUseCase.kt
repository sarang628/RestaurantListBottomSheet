package com.sarang.torang

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

interface GetSearchedRestaurantUseCase {
    fun invoke(coroutineScope: CoroutineScope) : StateFlow<List<RestaurantItemUiState>>
}