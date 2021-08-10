package moe.nikolay.nwcode.repository.images.models

class ImageModel {
    data class Model(
        val id: Long,
        val type: String,
        val tags: String,
        val preview: Preview,
        val largeImageURL: String,
        val webFormat: WebFormat,
        val width: Int,
        val height: Int,
        val size: Long,
        val user: User,
        val stats: Stats
    )

    data class Stats(
        val views: Long,
        val downloads: Long,
        val collections: Long,
        val likes: Long,
        val comments: Long,
    )

    data class Preview(
        val url: String,
        val width: Int,
        val height: Int,
    )

    data class WebFormat(
        val URL: String,
        val width: Int,
        val height: Int,
    )

    data class User(
        val id: Long,
        val name: String,
        val imageURL: String
    )
}
