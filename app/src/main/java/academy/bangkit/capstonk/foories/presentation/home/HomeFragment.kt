package academy.bangkit.capstonk.foories.presentation.home

import academy.bangkit.capstonk.foories.R
import academy.bangkit.capstonk.foories.core.config.Constants
import academy.bangkit.capstonk.foories.core.ui.FoodAdapter
import academy.bangkit.capstonk.foories.databinding.FragmentMainBinding
import academy.bangkit.capstonk.foories.presentation.detector.DetectorActivity
import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {

    companion object {
        const val REQUEST_CODE = 101
    }

    private val mainViewModel: HomeViewModel by viewModel()

    private lateinit var foodAdapter: FoodAdapter
    private lateinit var binding: FragmentMainBinding
    private lateinit var photoPath: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val context = binding.root.context
        val pref = context.getSharedPreferences(Constants.PREFS, Context.MODE_PRIVATE)
        val userName = pref.getString(Constants.USER_FULL_NAME, "")
        val userCalorie = pref.getInt(Constants.USER_CALORIE, 0)
        foodAdapter = FoodAdapter(binding.root.context)

        with(binding.rvFood) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = foodAdapter
        }

        mainViewModel.getUserTodayTotalCalories().observe(viewLifecycleOwner, {
            val calorie = it?.toInt() ?: 0
            val percentage = (calorie * 100 / userCalorie)
            binding.userName.text = userName
            binding.percentage.text = getString(R.string.calorie_percentage, percentage)
            binding.calorieProgress.text =
                getString(R.string.calorie_progress, calorie, userCalorie)
            val progress = if (percentage >= 100) 75 else (0.75 * percentage).toInt()
            val colorId = if (percentage >= 100) R.color.light_red else R.color.primary
            val color = ContextCompat.getColor(context, colorId)
            binding.progressIndicator.progress = progress
            binding.percentage.setTextColor(color)
            binding.progressIndicator.setIndicatorColor(color)
        })

        mainViewModel.getUserTodayFoods().observe(viewLifecycleOwner, {
            foodAdapter.foods = it
            binding.errorMessage.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
        })

        binding.fabAdd.setOnClickListener {
            checkPermission(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(p0: MultiplePermissionsReport?) {
                    takePhotoFromCamera()
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: MutableList<PermissionRequest>?,
                    p1: PermissionToken?
                ) {
                    val message = "Camera must be allowed"
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun checkPermission(permissionListener: MultiplePermissionsListener) {
        Dexter.withContext(requireActivity())
            .withPermissions(
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            .withListener(permissionListener).check()
    }

    private fun takePhotoFromCamera() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(requireActivity().packageManager)?.also {
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    null
                }
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        requireActivity(),
                        "academy.bangkit.capstonk.foories.fileprovider",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_CODE)
                }
            }
        }
    }

    private fun createImageFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CANADA).format(Date())
        val storageDir: File? =
            binding.root.context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            photoPath = absolutePath
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == AppCompatActivity.RESULT_OK) {
            val intent = Intent(context, DetectorActivity::class.java)
            intent.putExtra(DetectorActivity.IMAGE_PATH, photoPath)
            startActivity(intent)
        }
    }
}