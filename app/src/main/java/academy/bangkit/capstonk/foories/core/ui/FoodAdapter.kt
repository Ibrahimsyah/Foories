package academy.bangkit.capstonk.foories.core.ui

import academy.bangkit.capstonk.foories.R
import academy.bangkit.capstonk.foories.core.domain.model.Food
import academy.bangkit.capstonk.foories.databinding.ItemFoodBinding
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class FoodAdapter(private val context: Context) :
    RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {
    var foods: List<Food> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    inner class FoodViewHolder(val binding: ItemFoodBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(food: Food) {
            binding.foodTitle.text = food.name
            binding.foodCalorie.text =
                context.getString(R.string.calorie_label, food.calories.toInt())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.bind(foods[position])
    }

    override fun getItemCount(): Int = foods.size
}