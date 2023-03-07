package prime.projects.mysafefly.addons

import androidx.annotation.DrawableRes

data class TravelAddOn(
    val id: Int,
    val label: String,
    val price: Float,
    @DrawableRes val icon: Int
)
