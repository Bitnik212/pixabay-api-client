package moe.nikolay.nwcode.repository.api.pixaby.models

class PixabayImagesModel {

    enum class Categories(val category: String) {
        Backgrounds("backgrounds"),
        Fashion("fashion"),
        Nature("nature"),
        Science("science"),
        Education("education"),
        Feelings("feelings"),
        Health("health"),
        People("people"),
        Religion("religion"),
        Places("places"),
        Animals("animals"),
        Industry("industry"),
        Computer("computer"),
        Food("food"),
        Sports("sports"),
        Transportation("transportation"),
        Travel("travel"),
        Buildings("buildings"),
        Business("business"),
        Music("music")
    }

    enum class Colors(val color: String) {
        Grayscale("grayscale"),
        Transparent("transparent"),
        Red("red"),
        Orange("orange"),
        Yellow("yellow"),
        Green("green"),
        Turquoise("turquoise"),
        Blue("blue"),
        Lilac("lilac"),
        Pink("pink"),
        White("white"),
        Gray("gray"),
        Black("black"),
        Brown("brown")
    }

    enum class LanguageCode(val lang: String) {
        CS("cs"),
        DA("da"),
        EN("en"),
        RU("ru")
    }

    class Request {
        /**
         * Поиск изображения
         *
         * @param key Your API key
         * @param q A URL encoded search term. If omitted, all images are returned. This value may not exceed 100 characters. Example: "yellow+flower"
         * @param lang Language code of the language to be searched in. Accepted values: cs, da, de, en, es, fr, id, it, hu, nl, no, pl, pt, ro, sk, fi, sv, tr, vi, th, bg, ru, el, ja, ko, zh
        Default: "en"
         * @param id Retrieve individual images by ID.
         * @param image_type Filter results by image type. Accepted values: "all", "photo", "illustration", "vector" Default: "all"
         * @param orientation Whether an image is wider than it is tall, or taller than it is wide. Accepted values: "all", "horizontal", "vertical" Default: "all"
         * @param category Filter results by category. Accepted values: backgrounds, fashion, nature, science, education, feelings, health, people, religion, places, animals, industry, computer, food, sports, transportation, travel, buildings, business, music
         * @param min_width Minimum image width. Default: "0"
         * @param min_height Minimum image height. Default: "0"
         * @param colors Filter images by color properties. A comma separated list of values may be used to select multiple properties. Accepted values: "grayscale", "transparent", "red", "orange", "yellow", "green", "turquoise", "blue", "lilac", "pink", "white", "gray", "black", "brown"
         * @param editors_choice Select images that have received an Editor's Choice award. Accepted values: "true", "false" Default: "false"
         * @param safesearch A flag indicating that only images suitable for all ages should be returned. Accepted values: "true", "false" Default: "false"
         * @param order How the results should be ordered. Accepted values: "popular", "latest" Default: "popular"
         * @param page Returned search results are paginated. Use this parameter to select the page number. Default: 1
         * @param per_page Determine the number of results per page. Accepted values: 3 - 200 Default: 20
         * @param callback JSONP callback function name
         * @param pretty Indent JSON output. This option should not be used in production. Accepted values: "true", "false" Default: "false"
         * **/
        data class SearchImages(
            val key: String,
            val q: String,
            var lang: String = "en",
            val id: String,
            var image_type: String = "all",
            var orientation: String = "all",
            val category: String,
            var min_width: Int = 0,
            var min_height: Int = 0,
            val colors: String,
            var editors_choice: Boolean = false,
            var safesearch: Boolean = false,
            var order: String = "popular",
            var page: Int = 1,
            var per_page: Int = 20,
            val callback: String,
            var pretty: Boolean = false
        )
    }

    class Response {
        data class Image(
            val id: Long,
            val pageURL: String,
            val type: String,
            val tags: String,
            val previewURL: String,
            val previewWidth: Int,
            val previewHeight: Int,
            val webformatURL: String,
            val webformatWidth: Int,
            val webformatHeight: Int,
            val largeImageURL: String,
            val imageWidth: Int,
            val imageHeight: Int,
            val imageSize: Long,
            val views: Long,
            val downloads: Long,
            val collections: Long,
            val likes: Long,
            val comments: Long,
            val user_id: Long,
            val user: String,
            val userImageURL: String
        )

        data class SearchImages(
            val total: Long,
            val totalHits: Int,
            val hits: List<Image>
        )
    }

}