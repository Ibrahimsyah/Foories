package academy.bangkit.capstonk.foories.presentation.main

import academy.bangkit.capstonk.foories.R
import academy.bangkit.capstonk.foories.core.config.Constants
import academy.bangkit.capstonk.foories.core.domain.model.Food
import academy.bangkit.capstonk.foories.core.ui.FoodAdapter
import academy.bangkit.capstonk.foories.databinding.ActivityMainBinding
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var foodAdapter: FoodAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        foodAdapter = FoodAdapter(this)
        val pref = getSharedPreferences(Constants.PREFS, Context.MODE_PRIVATE)
        val userName = pref.getString(Constants.USER_FULL_NAME, "")
        val userCalorie = pref.getInt(Constants.USER_CALORIE, 0)

        with(binding.rvFood) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = foodAdapter
        }

        mainViewModel.getUserTodayTotalCalories().observe(this, {
            val calorie = it?.toInt() ?: 0
            val percentage = (calorie * 100 / userCalorie)
            binding.userName.text = userName
            binding.percentage.text = getString(R.string.calorie_percentage, percentage)
            binding.progressIndicator.progress = (0.75 * percentage).toInt()
            binding.calorieProgress.text =
                getString(R.string.calorie_progress, calorie, userCalorie)
            if (percentage >= 100) {
                binding.progressIndicator.progress = 75
                val color = ContextCompat.getColor(this, R.color.light_red)
                binding.percentage.setTextColor(color)
                binding.progressIndicator.setIndicatorColor(color)
            }
        })

        mainViewModel.getUserTodayFoods().observe(this, {
            foodAdapter.foods = it
            binding.errorMessage.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
        })

        binding.fabAdd.setOnClickListener {
            //TODO: Change this to i
            val food = Food("Food", 200.0, Date())
            mainViewModel.insertFood(food)
            Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show()
        }
    }
}