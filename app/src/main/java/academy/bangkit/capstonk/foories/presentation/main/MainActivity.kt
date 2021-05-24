package academy.bangkit.capstonk.foories.presentation.main

import academy.bangkit.capstonk.foories.core.config.Constants
import academy.bangkit.capstonk.foories.databinding.ActivityMainBinding
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = getSharedPreferences(Constants.PREFS, Context.MODE_PRIVATE)
        val userName = pref.getString(Constants.USER_FULL_NAME, "")

        binding.userName.text = userName
    }
}