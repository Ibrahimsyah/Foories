package academy.bangkit.capstonk.foories.presentation.screening

import academy.bangkit.capstonk.foories.R
import academy.bangkit.capstonk.foories.core.config.Constants
import academy.bangkit.capstonk.foories.databinding.ActivitySuccessBinding
import academy.bangkit.capstonk.foories.presentation.home.HomeActivity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SuccessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = getSharedPreferences(Constants.PREFS, Context.MODE_PRIVATE)
        val calorie = pref.getInt(Constants.USER_CALORIE, 0)
        binding.description.text = getString(R.string.calorie_information, calorie)
        binding.btnStart.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}