package moe.nikolay.nwcode.ui.home.category_photos

import android.app.ActionBar
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import moe.nikolay.nwcode.databinding.FragmentCategoryPhotosBinding
import moe.nikolay.nwcode.repository.api.pixaby.models.PixabayImagesModel
import moe.nikolay.nwcode.repository.images.models.ImageModel
import moe.nikolay.nwcode.ui.adapter.ImagesAdapter

class CategoryPhotosFragment : Fragment() {
    private val TAG = this::class.java.simpleName
    private lateinit var viewModel: CategoryPhotosViewModel
    private var _binding: FragmentCategoryPhotosBinding? = null
    private var categoryId: Int? = null
    private lateinit var category: PixabayImagesModel.Categories
    private lateinit var adapter: ImagesAdapter
    private lateinit var navCantroller: NavController

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CategoryPhotosViewModel::class.java)
        navCantroller = findNavController()
        categoryId = arguments?.getInt("categoryId")
        category = getCategoryById(categoryId!!)
        viewModel.initImagesByCategory(category = category.category_en)
        adapter = ImagesAdapter()

        activity?.actionBar?.title = category.category_en
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoryPhotosBinding.inflate(inflater, container, false)

        val recyclerView = binding.recyclerView
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        viewModel.imagesByCategory.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                adapter.addData(it)
                binding.progressBar.visibility = View.GONE
            } else {
                binding.progressBar.visibility = View.VISIBLE
            }
        })

        adapter.callback = object : ImagesAdapter.Callback {
            override fun onClick(image: ImageModel.Model) {
                TODO("Not yet implemented")
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getCategoryById(categoryId: Int): PixabayImagesModel.Categories {
        val categories = PixabayImagesModel.Categories.values()
        return categories.find { it.id.toInt() == categoryId }!!
    }
}