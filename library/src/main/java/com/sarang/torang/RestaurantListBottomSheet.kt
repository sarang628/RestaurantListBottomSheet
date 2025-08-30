package com.sarang.torang

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun RestaurantListBottomSheet(modifier : Modifier,
                              viewModel: RestaurantListBottomSheetViewModel = hiltViewModel(),
                              scaffoldState: BottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
                              sheetPeekHeight: Dp = BottomSheetDefaults.SheetPeekHeight,
                              content: @Composable (PaddingValues) -> Unit = {}) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    BottomSheetScaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        sheetContent = { RestaurantList(modifier = modifier, restaurantList = uiState) },
        sheetPeekHeight = sheetPeekHeight,
        content = content )
}

@Preview
@Composable
fun RestaurantList(modifier : Modifier, restaurantList : List<RestaurantItemUiState> = listOf()){
    LazyColumn(modifier) {
        items(restaurantList.size) {
            Column {
                RestaurantItem(restaurantList.get(it))
                Spacer(Modifier.height(4.dp).fillMaxWidth().background(Color(0xEEEEEEEE)))
            }
        }
    }
}