package moe.nikolay.nwcode.helper.convertor

import moe.nikolay.nwcode.repository.api.pixaby.models.PixabayImagesModel
import moe.nikolay.nwcode.repository.images.models.ImageModel


fun PixabayImagesModel.Response.Image.toImageModel(): ImageModel.Model {

    val preview = ImageModel.Preview(
        url = this.previewURL,
        width = this.previewWidth,
        height = this.previewHeight
    )
    val webFormat = ImageModel.WebFormat(
        URL = this.webformatURL,
        width = this.webformatWidth,
        height = this.webformatHeight
    )
    val user = ImageModel.User(
        id = this.user_id,
        name = this.user,
        imageURL = this.userImageURL
    )
    val stats = ImageModel.Stats(
        views = this.views,
        downloads = this.downloads,
        collections = this.collections,
        likes = this.likes,
        comments = this.comments
    )
    return ImageModel.Model(
        id = this.id,
        type = this.type,
        tags = this.tags,
        preview = preview,
        largeImageURL = this.largeImageURL,
        webFormat = webFormat,
        width = this.imageWidth,
        height = this.imageHeight,
        size = this.imageSize,
        user = user,
        stats = stats
    )
}

fun List<PixabayImagesModel.Response.Image>.toImagesModel(): List<ImageModel.Model> {
    val result = mutableListOf<ImageModel.Model>()
    this.forEach {
        result.add(it.toImageModel())
    }
    return result
}
