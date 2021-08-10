package moe.nikolay.nwcode.ui.home.category_photos

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import moe.nikolay.nwcode.repository.images.ImagesRepository
import moe.nikolay.nwcode.repository.images.models.ImageModel

class CategoryPhotosViewModel (application: Application) : AndroidViewModel(application) {
    private var TAG = this::class.java.simpleName
    private val context: Context
        get() = getApplication<Application>().applicationContext
    private val repository = ImagesRepository()
    val imagesByCategory: MutableLiveData<List<ImageModel.Model>?> = MutableLiveData(null)

    fun initImagesByCategory(category: String) {
        repository.getImageListBySearch(query = category, callback = object : ImagesRepository.ImageListCallback {
            override fun onSuccess(data: List<ImageModel.Model>) {
                imagesByCategory.value = data
            }

            override fun onFailure(t: Throwable) {
                imagesByCategory.value = null
                Log.e(TAG, "onFailure: $t")
            }
        })
    }

}