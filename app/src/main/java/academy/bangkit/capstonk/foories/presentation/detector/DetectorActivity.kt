package academy.bangkit.capstonk.foories.presentation.detector

import academy.bangkit.capstonk.foories.core.ui.LoadingDialog
import academy.bangkit.capstonk.foories.core.util.Mapper
import academy.bangkit.capstonk.foories.databinding.ActivityDetectorBinding
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.exifinterface.media.ExifInterface
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

class DetectorActivity : AppCompatActivity() {
    companion object {
        const val IMAGE_PATH = "IMAGE_PATH"
    }

    private lateinit var binding: ActivityDetectorBinding
    private lateinit var loadingDialog: LoadingDialog
    private lateinit var bottomSheetDialog: BottomSheetDialog
    private val viewModel: DetectorViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetectorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadingDialog = LoadingDialog(this)
        bottomSheetDialog = BottomSheetDialog.getInstance(supportFragmentManager) {
            finish()
        }

        setSupportActionBar(binding.toolbar)
        val imagePath = intent.getStringExtra(IMAGE_PATH) as String
        val imageFile = File(imagePath)
        val bitmap = BitmapFactory.decodeFile(imageFile.absolutePath).run {
            val exif = ExifInterface(imageFile.absolutePath)
            val orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1)
            val matrix = Matrix()
            when (orientation) {
                6 -> {
                    matrix.postRotate(90F)
                }
                3 -> {
                    matrix.postRotate(180F)
                }
                8 -> {
                    matrix.postRotate(270F)
                }
            }
            Bitmap.createBitmap(this, 0, 0, this.width, this.height, matrix, true)
        }
        binding.foodImage.setImageBitmap(bitmap)
        viewModel.getLoading().observe(this, {
            loadingDialog.apply {
                if (it) startLoading("Detecting Calories of Food") else stopLoading()
            }
        })

        viewModel.detectImage(bitmap).observe(this, {
            val mappedResult = Mapper.detectionResultToDomain(it)
            bottomSheetDialog.showResult(mappedResult) { foodIndex ->
                val selectedFood = Mapper.detectionResultToFood(mappedResult[foodIndex])
                viewModel.saveFood(selectedFood)
                Toast.makeText(this, "Food Added", Toast.LENGTH_SHORT).show()
                finish()
                true
            }
        })
    }

}