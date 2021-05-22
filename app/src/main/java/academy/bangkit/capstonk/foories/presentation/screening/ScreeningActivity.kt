package academy.bangkit.capstonk.foories.presentation.screening

import academy.bangkit.capstonk.foories.databinding.ActivityScreeningBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager

class ScreeningActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScreeningBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScreeningBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val activityData = ActivityCategory.generateData()
        val adapter = CategoryAdapter(activityData)
        with(binding.rvActivity){
            layoutManager = LinearLayoutManager(this@ScreeningActivity)
            setHasFixedSize(true)
            this.adapter = adapter
        }
    }
}