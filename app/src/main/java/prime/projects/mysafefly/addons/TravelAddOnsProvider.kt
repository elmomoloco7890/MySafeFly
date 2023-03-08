package prime.projects.mysafefly.addons

import prime.projects.mysafefly.R

object TravelAddOnsProvider {
    private val addOns = listOf(
        TravelAddOn(0, "Comfort Seat", 30F, R.drawable.ic_comfort_seat),
        TravelAddOn(1, "On-board Shopping", 10F, R.drawable.ic_onboard_shopping),
        TravelAddOn(2, "Internet Connection", 30F, R.drawable.ic_internet_connection),
        TravelAddOn(3, "Cancellation Cover", 40F, R.drawable.ic_cancellation_cover),
        TravelAddOn(4, "Lounge", 50F, R.drawable.ic_lounge)
    )

    fun get(): List<TravelAddOn> {
        return addOns
    }

    fun get(addOnId: Int): TravelAddOn{
        return addOns.firstOrNull {
            addOn -> addOn.id == addOnId
        }?: throw IllegalArgumentException("Invalid add-on id $addOnId")
    }
}