package com.sarang.torang

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet

data class RestaurantItemUiState(
    val restaurantName : String = "",
    val rating : Float = 0.0f,
    val ratingCount : Int = 0,
    val price : String = "",
    val foodType : String = "",
    val open : String = "",
    val closes : String = "",
    val imageList : List<String> = listOf()
){
    companion object
}

val RestaurantItemUiState.Companion.Sample : RestaurantItemUiState get() =
    RestaurantItemUiState(
        restaurantName = "Cuncina Venti Restaurant",
        rating = 4.2f,
        ratingCount = 1790,
        price = "$50-100",
        foodType = "Italian",
        open = "Open",
        closes = "Closes 9 PM",
        imageList = listOf("","","","")
    )

@Preview
@Composable
fun RestaurantItem(uiState: RestaurantItemUiState = RestaurantItemUiState.Companion.Sample){
    ConstraintLayout(modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 12.dp), constraintSet = restaurantItemConstraintSet) {
        Text(modifier = Modifier.layoutId("restaurantName"),text = uiState.restaurantName,      fontSize = 18.sp)
        Text(modifier = Modifier.layoutId("rating"),        text = uiState.rating.toString(),   fontSize = 14.sp)
        Text(modifier = Modifier.layoutId("ratingCount"),   text = "(${uiState.ratingCount})",  fontSize = 14.sp, color = Color.Gray)
        Text(modifier = Modifier.layoutId("dot"),           text = "*",                         fontSize = 14.sp, color = Color.Gray)
        Text(modifier = Modifier.layoutId("price"),         text = uiState.price,               fontSize = 14.sp, color = Color.Gray)
        Text(modifier = Modifier.layoutId("dot1"),          text = "*",                         fontSize = 14.sp, color = Color.Gray)
        Text(modifier = Modifier.layoutId("foodType"),      text = uiState.foodType,            fontSize = 14.sp, color = Color.Gray)
        Text(modifier = Modifier.layoutId("open"),          text = "Open",                      fontSize = 14.sp, color = Color(0xFF4FA267))
        Text(modifier = Modifier.layoutId("dot2"),          text = "*",                         fontSize = 14.sp, color = Color.Gray)
        Text(modifier = Modifier.layoutId("closes"),        text = uiState.closes,              fontSize = 14.sp, color = Color.Gray)
        Icon(modifier = Modifier.layoutId("ratingIcon").size(13.dp), imageVector = Icons.Default.Star, contentDescription = "")
        LazyRow(modifier = Modifier.layoutId("imageList"), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            items(uiState.imageList.size) {
                Column {
                    LocalRestaurantItemImageLoader.current.invoke(Modifier.size(120.dp).clip(RoundedCornerShape(16.dp)), uiState.imageList[it] , null, null, ContentScale.Crop)
                }
            }
        }
    }
}

val restaurantItemConstraintSet : ConstraintSet = ConstraintSet {
    val restaurantName  = createRefFor("restaurantName")
    val rating          = createRefFor("rating")
    val ratingIcon      = createRefFor("ratingIcon")
    val ratingCount     = createRefFor("ratingCount")
    val dot             = createRefFor("dot")
    val price           = createRefFor("price")
    val dot1            = createRefFor("dot1")
    val foodType        = createRefFor("foodType")
    val open            = createRefFor("open")
    val dot2            = createRefFor("dot2")
    val closes          = createRefFor("closes")
    val imageList       = createRefFor("imageList")

    constrain(restaurantName)   {  start.linkTo(parent.start);          top.linkTo(parent.top) }
    constrain(rating)           {  start.linkTo(parent.start);          top.linkTo(restaurantName.bottom) }
    constrain(ratingIcon)       {  start.linkTo(rating.end, 2.dp);      top.linkTo(rating.top); bottom.linkTo(rating.bottom) }
    constrain(ratingCount)      {  start.linkTo(ratingIcon.end, 2.dp);  top.linkTo(rating.top); bottom.linkTo(rating.bottom) }
    constrain(dot)              {  start.linkTo(ratingCount.end, 2.dp); top.linkTo(rating.top); bottom.linkTo(rating.bottom) }
    constrain(price)            {  start.linkTo(dot.end, 2.dp);         top.linkTo(rating.top); bottom.linkTo(rating.bottom) }
    constrain(dot1)             {  start.linkTo(price.end, 2.dp);       top.linkTo(rating.top); bottom.linkTo(rating.bottom) }
    constrain(foodType)         {  start.linkTo(dot1.end, 2.dp);        top.linkTo(rating.top); bottom.linkTo(rating.bottom) }
    constrain(open)             {  start.linkTo(parent.start);          top.linkTo(rating.bottom) }
    constrain(dot2)             {  start.linkTo(open.end, 2.dp);        top.linkTo(open.top); bottom.linkTo(open.bottom) }
    constrain(closes)           {  start.linkTo(dot2.end, 2.dp);        top.linkTo(open.top) }
    constrain(imageList)        {  start.linkTo(parent.start);          top.linkTo(open.bottom, 8.dp) }
}