package moe.nikolay.nwcode.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import moe.nikolay.nwcode.R
import moe.nikolay.nwcode.databinding.FragmentHomeBinding
import moe.nikolay.nwcode.ui.adapter.CategoriesAdapter

class HomeFragment : Fragment() {
    private val TAG = this::class.java.simpleName
    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var navController: NavController

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoriesAdapter = CategoriesAdapter()
        navController = findNavController()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val recyclerView = binding.recyclerView
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = categoriesAdapter

        categoriesAdapter.callback = object : CategoriesAdapter.Callback {
            override fun onClick(categoryId: Int) {
                val bundle = Bundle()
                bundle.putInt("categoryId", categoryId)
                Log.d(TAG, "onClick: categoryId is $categoryId")
                navController.navigate(R.id.categoryPhotosFragment, bundle)
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}