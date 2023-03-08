package prime.projects.mysafefly.travel_information

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import prime.projects.mysafefly.addons.TravelAddOnsProvider
import prime.projects.mysafefly.databinding.FragmentTravelInformationBinding
import android.text.TextUtils
import androidx.fragment.app.activityViewModels
import prime.projects.mysafefly.model.MySafeFlyViewModel


class TravelInformationFragment : Fragment() {

    private lateinit var binding: FragmentTravelInformationBinding

    private val clickListener = TravelAddOnsClickListener()

    private var isClicked: Boolean? = null

    private val sharedViewModel: MySafeFlyViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        val travelBinding = FragmentTravelInformationBinding.inflate(inflater, container, false)
        binding = travelBinding
        return travelBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            travelInformationFragment = this@TravelInformationFragment
        }

        setUpAddOns(clickListener)
    }

    private fun setUpAddOns(clickListener: TravelAddOnsClickListener) {
        val addOns = TravelAddOnsProvider.get()

        with(binding.layoutTravelAddOns.travelAddOns){
            adapter = TravelAddOnsAdapter(addOns, clickListener)
            layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }
    }

    private fun getTravelerInformation(): TravelerInformation {
        return TravelerInformation(
            fullName = binding.layoutTravelInformation.fullNameEditText.text.toString(),
            age = try {
                binding.layoutTravelInformation.ageEditText.text.toString().toInt()
            } catch (exception: NumberFormatException) {
                0
            },
            passportNumber = binding.layoutTravelInformation.passportNumberEditText.text.toString()
        )
    }

    private fun getAddOns(clickListener: TravelAddOnsClickListener): IntArray {
        return clickListener.get().map { addOn -> addOn.id }.toIntArray()
    }

    private fun getPromoCode(): String {
        return binding.layoutPromoCode.promoCodeEditText.text.toString()
    }

    fun nextClicked(){
        val travelerInformation = getTravelerInformation()
        val addOns = getAddOns(clickListener)
        val promoCodes = getPromoCode()
        val directions = TravelInformationFragmentDirections.actionTravelInformationFragmentToConfirmationFragment(
            travelerInformation,
            addOns,
            promoCodes)
        if (TextUtils.isEmpty(binding.layoutTravelInformation.ageEditText.text.toString())){
            sharedViewModel.makingShortToastMessages(requireActivity(), "Please no empty strings")
        } else if (TextUtils.isEmpty(binding.layoutTravelInformation.fullNameEditText.text.toString())){
            sharedViewModel.makingShortToastMessages(requireActivity(), "Please fill out your name")
        } else if (TextUtils.isEmpty(binding.layoutTravelInformation.passportNumberEditText.text.toString())){
            sharedViewModel.makingShortToastMessages(requireActivity(), "Please fill out a number")
        } else if (TextUtils.isEmpty(binding.layoutPromoCode.promoCodeEditText.text.toString())){
            sharedViewModel.makingShortToastMessages(requireActivity(), "Fill out a promo-code number")
        } else {
            findNavController().navigate(directions)
        }

    }

}