package com.jetbrains.kmm.androidApp.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.jetbrains.androidApp.R

val gothicA1 = FontFamily(
        Font(R.font.gothica1_regular, FontWeight.Normal),
        Font(R.font.gothica1_medium, FontWeight.Medium),
        Font(R.font.gothica1_semibold, FontWeight.SemiBold),
        Font(R.font.gothica1_bold, FontWeight.Bold),
        Font(R.font.gothica1_black, FontWeight.Black),
    )

val inriaSerif = FontFamily(
    Font(R.font.inriaserif_bold, FontWeight.Bold),
    Font(R.font.inriaserif_italic, FontWeight.SemiBold),
    Font(R.font.inriaserif_regular, FontWeight.Normal),
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        color = Black,
        fontFamily = inriaSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    h1 = TextStyle(
        color = DarkGray,
        fontFamily = inriaSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
    ),
    h2 = TextStyle(
        color = DarkGray,
        fontFamily = inriaSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    h3 = TextStyle(
        color = DarkGray,
        fontFamily = inriaSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    )
)