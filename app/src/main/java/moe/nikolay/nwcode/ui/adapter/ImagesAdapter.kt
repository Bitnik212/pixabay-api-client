package moe.nikolay.nwcode.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.load
import coil.request.ImageRequest
import moe.nikolay.nwcode.R
import moe.nikolay.nwcode.repository.images.models.ImageModel


class ImagesAdapter: RecyclerView.Adapter<ImagesAdapter.ViewHolder>() {
    private val TAG = this::class.java.simpleName
    var callback: Callback? = null
    private var mData: MutableList<ImageModel.Model> = mutableListOf()


    interface Callback {
        fun onClick(image: ImageModel.Model)
    }

    class ViewHolder internal constructor(view: View, val viewType: Int) : RecyclerView.ViewHolder(view) {
        val view = view
        val imageView = view.findViewById<ImageView>(R.id.image_card_image_view)
        val progressBar = view.findViewById<ProgressBar>(R.id.image_card_progress_bar)
    }

    fun addData(images: List<ImageModel.Model>) {
        val size = mData.size
        mData.addAll(images)
        val newSize = mData.size
        notifyItemRangeChanged(size, newSize)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.component_image_card, parent, false)
        return ViewHolder(view, viewType)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val nowImage = mData[position]
        holder.run {
            view.setOnClickListener {
                callback?.onClick(nowImage)
            }
        }
        loadImage(holder = holder, url = nowImage.preview.url)
    }

    private fun loadImage(holder: ViewHolder, url: String) {
        val imageLoader = ImageLoader.Builder(holder.view.context)
            .availableMemoryPercentage(0.25)
            .crossfade(true)
            .build()

        val request = ImageRequest.Builder(holder.view.context)
            .data(url)
            .target(onStart = { placeholder ->
                Log.d(TAG, "loadImage: plaseholder")
                    holder.imageView.visibility = View.GONE
                    holder.progressBar.visibility = View.VISIBLE
                },
                onSuccess = { result ->
                    Log.d(TAG, "loadImage: success")
                    holder.progressBar.visibility = View.GONE
                    holder.imageView.visibility = View.VISIBLE
                    holder.imageView.setImageDrawable(result)
                },
                onError = { error ->
                    Log.e(TAG, "loadImage: error $error")
                })
            .build()
        imageLoader.enqueue(request)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun getItemId(position: Int): Long {
        return mData[position].id
    }
}