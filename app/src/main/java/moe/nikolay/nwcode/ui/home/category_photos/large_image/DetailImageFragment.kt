package moe.nikolay.nwcode.ui.home.category_photos.large_image

import android.app.WallpaperManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.ImageLoader
import coil.load
import coil.request.ImageRequest
import moe.nikolay.nwcode.R
import moe.nikolay.nwcode.databinding.FragmentDetailImageBinding
import moe.nikolay.nwcode.repository.images.models.ImageModel


class DetailImageFragment : Fragment() {
    private val TAG = this::class.java.simpleName
    private lateinit var binding: FragmentDetailImageBinding
    private lateinit var viewModel: DetailImageViewModel
    private var imageId: Long = 0
    private var image: ImageModel.Model? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailImageViewModel::class.java)
        imageId = arguments?.getLong("imageId")!!
        viewModel.initImage(imageId = imageId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailImageBinding.inflate(inflater, container, false)

        viewModel.image.observe(viewLifecycleOwner, Observer {
            image = it
            if (it != null) {
                binding.progressBar.visibility = View.GONE
                binding.imageView.load(image!!.largeImageURL)
            } else {
                binding.progressBar.visibility = View.VISIBLE
            }
        })

        binding.materialButton.setOnClickListener {
            if (image != null) {
                viewModel.setWallPaper(image!!.largeImageURL)
            }
        }

        return binding.root
    }



}