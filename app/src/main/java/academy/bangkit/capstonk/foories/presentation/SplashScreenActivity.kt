package academy.bangkit.capstonk.foories.presentation

import academy.bangkit.capstonk.foories.databinding.ActivitySplashScreenBinding
import academy.bangkit.capstonk.foories.presentation.onboarding.OnboardingScreenActivity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashScreenActivity : AppCompatActivity() {
    companion object {
        const val SPLASH_TIMEOUT_MILS = 3000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        Handler(mainLooper).postDelayed({
            startActivity(Intent(this, OnboardingScreenActivity::class.java))
            finish()
        }, SPLASH_TIMEOUT_MILS)
    }
}