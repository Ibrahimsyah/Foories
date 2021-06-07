package academy.bangkit.capstonk.foories.presentation.detector

import academy.bangkit.capstonk.foories.core.domain.model.DetectionResult
import academy.bangkit.capstonk.foories.databinding.BottomSheetLayoutBinding
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetDialog private constructor(
    private val supportFragmentManager: FragmentManager,
    private val onCancel: () -> Unit
) :
    BottomSheetDialogFragment() {
    companion object {
        fun getInstance(
            supportFragmentManager: FragmentManager,
            onCancel: () -> Unit,
        ): BottomSheetDialog {
            return BottomSheetDialog(supportFragmentManager, onCancel)
        }
    }

    private lateinit var list: List<DetectionResult>
    private lateinit var cb: (Int) -> Boolean
    private lateinit var binding: BottomSheetLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetLayoutBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = DetectionResultAdapter(list)
        with(binding.rvDetectionResult) {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            this.adapter = adapter
        }
        binding.btnSubmit.setOnClickListener {
            val result = cb(adapter.selectedIndex)
            if (result) this.dismiss()
        }
    }

    fun showResult(list: List<DetectionResult>, cb: (Int) -> Boolean) {
        this.list = list
        this.cb = cb
        this.show(supportFragmentManager, "dialog")
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        onCancel()
    }
}