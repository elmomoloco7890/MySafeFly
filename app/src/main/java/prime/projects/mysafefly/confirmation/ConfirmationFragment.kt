package prime.projects.mysafefly.confirmation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import prime.projects.mysafefly.R
import prime.projects.mysafefly.addons.TravelAddOnsProvider
import prime.projects.mysafefly.databinding.FragmentConfirmationBinding
import prime.projects.mysafefly.model.MySafeFlyViewModel
import prime.projects.mysafefly.travel_information.TravelerInformation


class ConfirmationFragment : Fragment() {

    private lateinit var binding: FragmentConfirmationBinding

    private val args: ConfirmationFragmentArgs by navArgs()

    private val sharedViewModel: MySafeFlyViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        val confirmBinding = FragmentConfirmationBinding.inflate(inflater, container, false)
        binding = confirmBinding
        return confirmBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        if(bundle == null){
            sharedViewModel.makingLongToastMessages(requireActivity(), "ConfirmationFragment did not receive traveler information")
        }
        getArgs()
        binding.apply {
            confirmationFragment = this@ConfirmationFragment
        }
    }

    private fun getArgs() {
        showTravelerInformation(args.travelerInformation)
        showTravelAddOns(args.travelAddOns)
        showPromoCode(args.promoCode)
    }

    private fun showTravelerInformation(travelerInformation: TravelerInformation){
        binding.fullNameTextView.text = getString(R.string.traveler_information_full_name, travelerInformation.fullName)
        binding.ageTextView.text = getString(R.string.traveler_information_age, travelerInformation.age)
        binding.passportNumberTextView.text = getString(R.string.traveler_information_passport_number, travelerInformation.passportNumber)
    }

    fun confirmationClicked(){
        sharedViewModel.makingShortToastMessages(requireActivity(), "Travel information confirmed!")
        findNavController().navigate(R.id.action_confirmationFragment_to_travelInformationFragment)
    }

    private fun showTravelAddOns(travelAddOns: IntArray) {
        if (travelAddOns.isEmpty()) {
            binding.travelAddOnsTextView.text = getString(R.string.travel_add_ons_none)
            return
        }
        val addOns = StringBuilder()
        for (i in travelAddOns.indices) {
            val addOn = TravelAddOnsProvider.get(travelAddOns[i])
            addOns.append(addOn.label)
            if (i != travelAddOns.lastIndex) {
                addOns.append(", ")
            }
        }
        binding.travelAddOnsTextView.text = addOns.toString()
    }

    private fun showPromoCode(promoCode: String?) {
        if (promoCode.isNullOrBlank()) {
            binding.promoCodeTextView.text = getString(R.string.promo_code_none)
        } else {
            binding.promoCodeTextView.text = promoCode
        }
    }

    /*private fun makingToasts(activity: Context, toastMessage: String){
        Toast.makeText(activity, toastMessage, Toast.LENGTH_LONG).show()
    }*/
}