package academy.bangkit.capstonk.foories.presentation.home

import academy.bangkit.capstonk.foories.databinding.ActivityMainBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mainPagerAdapter = HomePagerAdapter(this)
        binding.viewpager.apply {
            adapter = mainPagerAdapter
            currentItem = 1
        }
    }
}