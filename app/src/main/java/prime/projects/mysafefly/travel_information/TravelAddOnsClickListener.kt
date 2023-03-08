package prime.projects.mysafefly.travel_information

import prime.projects.mysafefly.addons.TravelAddOn

class TravelAddOnsClickListener {

    private val addOns = mutableListOf<TravelAddOn>()
    fun remove(addOn: TravelAddOn) {
        addOns.remove(addOn)
    }

    fun get(): List<TravelAddOn> {
        return addOns.toList()
    }

    fun add(addOn: TravelAddOn){
        addOns.add(addOn)
    }
}