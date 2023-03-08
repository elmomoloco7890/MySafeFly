package prime.projects.mysafefly.travel_information

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TravelerInformation(
    val fullName: String,
    val age: Int,
    val passportNumber: String
) : Parcelable
