package prime.projects.mysafefly.travel_information

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import prime.projects.mysafefly.addons.TravelAddOn
import prime.projects.mysafefly.databinding.ListItemTravelAddOnBinding

class TravelAddOnsAdapter(
    private val addOns: List<TravelAddOn>,
    private val clickListener: TravelAddOnsClickListener
    ): RecyclerView.Adapter<TravelAddOnsAdapter.TravelAddOnViewHolder>() {

        class TravelAddOnViewHolder(private val binding: ListItemTravelAddOnBinding):RecyclerView.ViewHolder(binding.root){
            fun bind(addOn: TravelAddOn, clickListener: TravelAddOnsClickListener){
                binding.apply {
                    travelAddOn = addOn
                }
                itemView.setOnClickListener {
                    val visibility = binding.addOnCheckedOverlay.visibility
                    if (visibility == View.VISIBLE){
                        clickListener.remove(addOn)
                    } else {
                        clickListener.add(addOn)
                    }
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TravelAddOnViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemTravelAddOnBinding.inflate(inflater, parent, false)
        return TravelAddOnViewHolder(binding)
    }

    override fun getItemCount(): Int = addOns.size

    override fun onBindViewHolder(holder: TravelAddOnViewHolder, position: Int) =
        holder.bind(addOns[position], clickListener)

}