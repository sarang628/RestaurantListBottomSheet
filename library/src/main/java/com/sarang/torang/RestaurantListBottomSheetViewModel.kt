package com.sarang.torang

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RestaurantListBottomSheetViewModel @Inject constructor(
    getSearchedRestaurantUseCase : GetSearchedRestaurantUseCase
) : ViewModel() {
    val uiState = getSearchedRestaurantUseCase.invoke(viewModelScope)
}