package academy.bangkit.capstonk.foories.presentation.home

import academy.bangkit.capstonk.foories.R
import academy.bangkit.capstonk.foories.core.ui.FoodAdapter
import academy.bangkit.capstonk.foories.databinding.FragmentHistoryBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    private val homeViewModel: HomeViewModel by viewModel()
    private var colorInactive = -1
    private var colorActive = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val foodAdapter = FoodAdapter(binding.root.context)
        colorInactive = ContextCompat.getColor(requireContext(), R.color.primary)
        colorActive = ContextCompat.getColor(requireContext(), R.color.text_white)

        with(binding.rvHistory) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = foodAdapter
        }

        homeViewModel.getHistory30Days().observe(viewLifecycleOwner, {
            foodAdapter.foods = it
            binding.errorMessage.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
        })
    }
}