package academy.bangkit.capstonk.foories.presentation.main

import academy.bangkit.capstonk.foories.R
import academy.bangkit.capstonk.foories.core.config.Constants
import academy.bangkit.capstonk.foories.databinding.ActivityMainBinding
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = getSharedPreferences(Constants.PREFS, Context.MODE_PRIVATE)
        val userName = pref.getString(Constants.USER_FULL_NAME, "")
        val userCalorie = pref.getInt(Constants.USER_CALORIE, 0)

        val percentage = 75
        binding.userName.text = userName
        binding.percentage.text = getString(R.string.calorie_percentage, percentage)
        binding.progressIndicator.progress = (0.75 * percentage).toInt()
        binding.calorieProgress.text =
            getString(R.string.calorie_progress, percentage * userCalorie / 100, userCalorie)
        if (percentage >= 100) {
            val color = ContextCompat.getColor(this, R.color.light_red)
            binding.percentage.setTextColor(color)
            binding.progressIndicator.setIndicatorColor(color)
        }
    }
}