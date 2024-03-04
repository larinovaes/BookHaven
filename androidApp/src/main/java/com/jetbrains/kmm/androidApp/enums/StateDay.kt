package com.jetbrains.kmm.androidApp.enums

import androidx.annotation.DrawableRes
import com.jetbrains.androidApp.R

enum class StateDay(@DrawableRes icon: Int) {
    GOOD_MORNING(R.drawable.ic_night),
    GOOD_AFTERNOON(R.drawable.ic_afternoon),
    GOOD_NIGHT(R.drawable.ic_sleeping)
}
