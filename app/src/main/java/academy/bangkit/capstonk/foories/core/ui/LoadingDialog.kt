package academy.bangkit.capstonk.foories.core.ui

import academy.bangkit.capstonk.foories.databinding.LoadingIndicatorBinding
import android.app.Activity
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable

class LoadingDialog(activity: Activity) {
    private var alertDialog: AlertDialog
    private var binding: LoadingIndicatorBinding

    init {
        val builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        binding = LoadingIndicatorBinding.inflate(inflater)
        builder.setView(binding.root)
        alertDialog = builder.create()
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    fun startLoading(title: String) {
        binding.loadingText.text = title
        alertDialog.show()
    }

    fun stopLoading() {
        alertDialog.cancel()
    }
}