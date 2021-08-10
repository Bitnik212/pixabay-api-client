package moe.nikolay.nwcode.repository.images

import android.util.Log
import moe.nikolay.nwcode.helper.convertor.toImagesModel
import moe.nikolay.nwcode.repository.api.pixaby.PixabayAPI
import moe.nikolay.nwcode.repository.api.pixaby.models.PixabayImagesModel
import moe.nikolay.nwcode.repository.images.models.ImageModel

class ImagesRepository {
    interface ImageListCallback {
        fun onSuccess(data: List<ImageModel.Model>)
        fun onFailure(t: Throwable)
    }

    private val TAG = this::class.java.simpleName
    private val api = PixabayAPI()

    fun getImageCategoriesMap(language: String = "ru"): MutableMap<Int, String> {
        val categories = PixabayImagesModel.Categories.values()
        val localizedCategoriesMap: MutableMap<Int, String> = mutableMapOf()
        categories.forEach {
            localizedCategoriesMap.put(
                it.id.toInt(),
                if (language == "ru") it.category_ru
                else it.category_en
            )
        }
        return localizedCategoriesMap
    }

    fun getImageCategoriesList(language: String = "ru"): List<String> {
        val categories = PixabayImagesModel.Categories.values()
        val localizedCategories: MutableList<String> = mutableListOf()
        categories.forEach {
            localizedCategories.add(if (language == "ru") it.category_ru else it.category_en)
        }
        return localizedCategories
    }

    fun getImageListBySearch(query: String, page: Int = 1, count: Int = 20, callback: ImageListCallback) {
        api.searchImage(request = PixabayImagesModel.Request.SearchImages(
            q = query,
            page = page,
            per_page = count
        ), callback = object : PixabayAPI.SearchImageCallback {
            override fun onSuccess(data: PixabayImagesModel.Response.SearchImages) {
                callback.onSuccess(data = data.hits.toImagesModel())
            }

            override fun onFailure(t: Throwable) {
                callback.onFailure(t)
                Log.e(TAG, "getImagesBySearch: onFailure searchImage: $t")
            }
        })

    }

}