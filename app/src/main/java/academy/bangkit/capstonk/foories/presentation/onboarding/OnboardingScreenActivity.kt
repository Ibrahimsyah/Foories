package academy.bangkit.capstonk.foories.presentation.onboarding

import academy.bangkit.capstonk.foories.R
import academy.bangkit.capstonk.foories.core.config.Constants
import academy.bangkit.capstonk.foories.databinding.ActivityOnboardingScreenBinding
import academy.bangkit.capstonk.foories.presentation.screening.ScreeningActivity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2

class OnboardingScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityOnboardingScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val onboardingData = listOf(
            OnboardingData(
                "Calculate",
                "Find your daily calorie need by filling the screening form. Foories will helps you calculate it by just giving several information",
                R.drawable.ic_fill_form
            ),
            OnboardingData(
                "Scan",
                "Take a picture of the food you are eating and let Foories detect and calculate the average calories in your food",
                R.drawable.ic_scan_food
            ),
            OnboardingData(
                "Monitor",
                "Keep your daily calories consumption below the threshold, it will make your body healthy",
                R.drawable.ic_analyze_calory
            ),
            OnboardingData(
                "Trace",
                "See your monthly food history by swiping the home screen to right",
                R.drawable.ic_history
            )
        )

        val viewPagerAdapter = OnboardingAdapter(this, onboardingData)
        val dotAdapter = DotAdapter(onboardingData.size)
        binding.viewpager.adapter = viewPagerAdapter
        binding.btnNext.setOnClickListener {
            with(binding.viewpager) {
                val currentItem = this.currentItem
                if (currentItem == onboardingData.size - 1) {
                    setOnboardingDone()
                }
                setCurrentItem(currentItem + 1, true)
            }
        }

        binding.btnSkip.setOnClickListener { setOnboardingDone() }

        with(binding.rvDot) {
            layoutManager = LinearLayoutManager(
                this@OnboardingScreenActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            setHasFixedSize(true)
            adapter = dotAdapter
        }

        binding.viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                dotAdapter.currentPosition = position
                binding.btnNext.text =
                    if (position == onboardingData.size - 1) "Get Started" else "Next"
            }
        })
    }

    private fun setOnboardingDone() {
        val prefs = getSharedPreferences(Constants.PREFS, Context.MODE_PRIVATE)
        prefs.edit().apply {
            putBoolean(Constants.ONBOARDING_PREF, true)
            apply()
        }
        startActivity(Intent(this, ScreeningActivity::class.java))
        finish()
    }
}