package moe.nikolay.nwcode.ui.home.category_photos.large_image

import android.app.Application
import android.app.WallpaperManager
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import coil.ImageLoader
import coil.request.ImageRequest
import moe.nikolay.nwcode.repository.images.ImagesRepository
import moe.nikolay.nwcode.repository.images.models.ImageModel

class DetailImageViewModel (application: Application) : AndroidViewModel(application) {
    private var TAG = this::class.java.simpleName
    private val context: Context
        get() = getApplication<Application>().applicationContext
    private val repository = ImagesRepository()
    val image: MutableLiveData<ImageModel.Model?> = MutableLiveData(null)

    fun initImage(imageId: Long) {
        repository.getImageById(imageId = imageId, callback = object : ImagesRepository.ImageCallback {
            override fun onSuccess(data: ImageModel.Model) {
                image.value = data
            }

            override fun onFailure(t: Throwable) {
                Log.e(TAG, "onFailure: $t")
                image.value = null
            }
        })
    }

    fun setWallPaper(url: String) {
        val imageLoader = ImageLoader.Builder(context)
            .availableMemoryPercentage(0.25)
            .crossfade(true)
            .build()
        val request = ImageRequest.Builder(context)
            .data(url)
            .target(
                onStart = {

                },
                onSuccess = {
                    val wallpaperManager = WallpaperManager.getInstance(context)
                    wallpaperManager.setBitmap(it.toBitmap())
                    Toast.makeText(context, "wallpaper setup complied", Toast.LENGTH_LONG).show()
                },
                onError = {
                    Log.e(TAG, "setWallPaper: $it")
                }
            )
            .build()

        imageLoader.enqueue(request)


    }

}