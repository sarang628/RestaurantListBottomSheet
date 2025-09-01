package com.sarang.torang

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantListBottomSheetViewModel @Inject constructor(
    getSearchedRestaurantUseCase : GetSearchedRestaurantUseCase,
    val selectRestaurantUseCase: SelectRestaurantUseCase
) : ViewModel() {
    fun setRestaurant(restaurantId: Int) {
        viewModelScope.launch {
            selectRestaurantUseCase.invoke(restaurantId)
        }
    }

    val uiState = getSearchedRestaurantUseCase.invoke(viewModelScope)
}