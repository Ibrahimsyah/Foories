package academy.bangkit.capstonk.foories.presentation.detector

import academy.bangkit.capstonk.foories.R
import academy.bangkit.capstonk.foories.core.domain.model.DetectionResult
import academy.bangkit.capstonk.foories.databinding.ItemDetectionResultBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class DetectionResultAdapter(private val data: List<DetectionResult>) :
    RecyclerView.Adapter<DetectionResultAdapter.DetectionViewHolder>() {
    var selectedIndex = -1
        private set

    inner class DetectionViewHolder(private val binding: ItemDetectionResultBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(detection: DetectionResult) {
            with(binding) {
                foodName.text = detection.name
                foodConfidence.text = itemView.context.getString(
                    R.string.confidence_percentage,
                    detection.confidence.toInt()
                )
                foodCalorie.text =
                    itemView.context.getString(R.string.calorie_label, detection.calorie.toInt())
                radio.isChecked = selectedIndex == adapterPosition
                radio.setOnClickListener { onItemClicked() }
            }
            itemView.setOnClickListener { onItemClicked() }
        }

        private fun onItemClicked() {
            val previousId = selectedIndex
            selectedIndex = adapterPosition
            notifyItemChanged(adapterPosition)
            notifyItemChanged(previousId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetectionViewHolder {
        val view =
            ItemDetectionResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetectionViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetectionViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}