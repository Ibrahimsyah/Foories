package academy.bangkit.capstonk.foories.presentation.screening

import academy.bangkit.capstonk.foories.R
import academy.bangkit.capstonk.foories.core.domain.model.User
import academy.bangkit.capstonk.foories.databinding.ActivityScreeningBinding
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel

class ScreeningActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScreeningBinding
    private lateinit var categoryAdapter: CategoryAdapter

    private val viewModel: ScreeningViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScreeningBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val activityData = ActivityCategory.generateData()
        categoryAdapter = CategoryAdapter(activityData)
        with(binding.rvActivity) {
            layoutManager = LinearLayoutManager(this@ScreeningActivity)
            setHasFixedSize(true)
            adapter = categoryAdapter
        }

        binding.btnSubmit.setOnClickListener {
            if (!checkFormCompletion()) {
                Toast.makeText(this, "Make sure all sections are filled", Toast.LENGTH_SHORT).show()
            } else {
                val name = binding.edName.text.toString()
                val height = binding.edHeight.text.toString().toDouble()
                val weight = binding.edWeight.text.toString().toDouble()
                val age = binding.edAge.text.toString().toInt()
                val gender = when (binding.radioGender.checkedRadioButtonId) {
                    R.id.male -> "male"
                    else -> "female"
                }
                val activityId = activityData[categoryAdapter.selectedIndex].id
                val user = User(
                    name,
                    gender,
                    height,
                    weight,
                    age,
                    activityId
                )
                viewModel.getUserCalories(user)
            }
        }
    }

    private fun checkFormCompletion(): Boolean {
        if (binding.edName.text.isNullOrEmpty()) return false
        if (binding.edHeight.text.isNullOrEmpty()) return false
        if (binding.edWeight.text.isNullOrEmpty()) return false
        if (binding.edAge.text.isNullOrEmpty()) return false
        if (binding.radioGender.checkedRadioButtonId == -1) return false
        if (categoryAdapter.selectedIndex == -1) return false
        return true
    }
}