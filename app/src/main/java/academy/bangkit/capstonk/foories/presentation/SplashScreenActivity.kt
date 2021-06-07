package academy.bangkit.capstonk.foories.presentation

import academy.bangkit.capstonk.foories.core.config.Constants
import academy.bangkit.capstonk.foories.databinding.ActivitySplashScreenBinding
import academy.bangkit.capstonk.foories.presentation.home.HomeActivity
import academy.bangkit.capstonk.foories.presentation.onboarding.OnboardingScreenActivity
import academy.bangkit.capstonk.foories.presentation.screening.ScreeningActivity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashScreenActivity : AppCompatActivity() {
    companion object {
        const val SPLASH_TIMEOUT_MILS = 2000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = getSharedPreferences(Constants.PREFS, Context.MODE_PRIVATE)
        val onboardingPref = pref.getBoolean(Constants.ONBOARDING_PREF, false)
        val screeningPref = pref.getBoolean(Constants.SCREENING_PREF, false)

        val nextActivity =
            //User opens the app first time
            if (!onboardingPref) OnboardingScreenActivity::class.java
            //User have not filled the Screening Form
            else if (!screeningPref) ScreeningActivity::class.java
            else HomeActivity::class.java

        Handler(mainLooper).postDelayed({
            startActivity(Intent(this, nextActivity))
            finish()
        }, SPLASH_TIMEOUT_MILS)
    }
}