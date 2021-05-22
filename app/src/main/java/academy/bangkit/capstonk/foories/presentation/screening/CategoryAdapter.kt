package academy.bangkit.capstonk.foories.presentation.screening

import academy.bangkit.capstonk.foories.databinding.ItemScreeningCategoryBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CategoryAdapter(private val data: List<ActivityCategory>) :
    RecyclerView.Adapter<CategoryAdapter.ActivityViewHolder>() {
    var selectedIndex = -1
        private set

    inner class ActivityViewHolder(private val binding: ItemScreeningCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(activity: ActivityCategory) {
            with(binding) {
                activityType.text = activity.title
                activityDescription.text = activity.description
                radio.isChecked = selectedIndex == adapterPosition
                radio.setOnClickListener { onItemClicked() }
            }
            itemView.setOnClickListener { onItemClicked() }
        }

        private fun onItemClicked(){
            val previousId = selectedIndex
            selectedIndex = adapterPosition
            notifyItemChanged(adapterPosition)
            notifyItemChanged(previousId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        val view =
            ItemScreeningCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ActivityViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}