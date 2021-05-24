package academy.bangkit.capstonk.foories.core.ui

import academy.bangkit.capstonk.foories.databinding.LoadingIndicatorBinding
import android.app.Activity
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable

class LoadingDialog(private val activity: Activity) {
    private lateinit var alertDialog: AlertDialog

    fun startLoading(title: String) {
        val builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater

        val binding = LoadingIndicatorBinding.inflate(inflater)
        binding.loadingText.text = title
        builder.setView(binding.root)

        alertDialog = builder.create()
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.show()
    }

    fun stopLoading() {
        alertDialog.cancel()
    }
}