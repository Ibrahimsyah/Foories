package academy.bangkit.capstonk.foories.presentation.onboarding

import academy.bangkit.capstonk.foories.databinding.FragmentOnboardingItemBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class OnboardingItemFragment : Fragment() {

    companion object {
        const val ONBOARDING_DATA = "ONBOARDING_DATA"
    }

    private lateinit var binding: FragmentOnboardingItemBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboardingItemBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = arguments?.getParcelable<OnboardingData>(ONBOARDING_DATA)
        data?.let {
            with(binding) {
                image.setImageResource(it.image)
                title.text = it.title
                description.text = it.description
            }
        }
    }
}