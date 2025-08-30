package com.sarang.torang

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sarang.torang.di.restaurant_list_bottom_sheet_di.CustomRestaurantItemImageLoader
import com.sarang.torang.repository.FindRepository
import com.sarang.torang.repository.test.FindRepositoryTest
import com.sryang.torang.ui.TorangTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var findRepository : FindRepository

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val scaffoldState = rememberBottomSheetScaffoldState()
            val coroutine = rememberCoroutineScope()

            LaunchedEffect(scaffoldState) {
                snapshotFlow { scaffoldState.bottomSheetState.currentValue }
            }

            TorangTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), floatingActionButton = { FloatingActionButton({coroutine.launch { scaffoldState.bottomSheetState.expand() }}){ Icon(Icons.AutoMirrored.Default.List, "") } }) { innerPadding ->
                    CompositionLocalProvider(LocalRestaurantItemImageLoader provides CustomRestaurantItemImageLoader) {
                        Box(Modifier.padding(innerPadding)){
                            RestaurantListBottomSheet(
                                modifier = Modifier.fillMaxHeight(),
                                sheetPeekHeight = 0.dp,
                                scaffoldState = scaffoldState) {
                                FindRepositoryTest(findRepository)
                            }
                        }
                    }
                }
            }
        }
    }
}