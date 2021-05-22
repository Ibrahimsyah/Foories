package academy.bangkit.capstonk.foories.presentation.onboarding

import academy.bangkit.capstonk.foories.presentation.onboarding.OnboardingItemFragment.Companion.ONBOARDING_DATA
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnboardingAdapter(activity: AppCompatActivity, val data: List<OnboardingData>) :
    FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = data.size

    override fun createFragment(position: Int): Fragment {
        return OnboardingItemFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ONBOARDING_DATA, data[position])
            }
        }
    }
}