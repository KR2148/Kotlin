package com.example.course_grid.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val stringResourceId: Int,
    val availableCourses: Int,
//    @StringRes val text: Int,
    @DrawableRes val imageResourceId: Int,

)
