package ua.marchuk.photoBuzzDailySnapshot.data.api.apiresponse

data class PhotoInfoResponse(
    val photo: Photo,
    val stat: String
)

data class Photo(
    val comments: Comments,
    val dates: Dates,
    val dateuploaded: String,
    val description: Description,
    val editability: Editability,
    val farm: Int,
    val id: String,
    val isfavorite: Int,
    val license: String,
    val media: String,
    val notes: Notes,
    val owner: Owner,
    val people: People,
    val publiceditability: Publiceditability,
    val rotation: Int,
    val safety_level: String,
    val secret: String,
    val server: String,
    val tags: Tags,
    val title: Title,
    val urls: Urls,
    val usage: Usage,
    val views: String,
    val visibility: Visibility
)

data class Comments(
    val _content: String
)

data class Dates(
    val lastupdate: String,
    val posted: String,
    val taken: String,
    val takengranularity: Int,
    val takenunknown: String
)

data class Description(
    val _content: String
)

data class Editability(
    val canaddmeta: Int,
    val cancomment: Int
)

data class Notes(
    val note: List<Any>
)

data class Owner(
    val gift: Gift,
    val iconfarm: Int,
    val iconserver: String,
    val location: Any,
    val nsid: String,
    val path_alias: String,
    val realname: String,
    val username: String
)

data class People(
    val haspeople: Int
)

data class Publiceditability(
    val canaddmeta: Int,
    val cancomment: Int
)

data class Tags(
    val tag: List<Tag>
)

data class Title(
    val _content: String
)

data class Urls(
    val url: List<Url>
)

data class Usage(
    val canblog: Int,
    val candownload: Int,
    val canprint: Int,
    val canshare: Int
)

data class Visibility(
    val isfamily: Int,
    val isfriend: Int,
    val ispublic: Int
)

data class Gift(
    val eligible_durations: List<String>,
    val gift_eligible: Boolean,
    val new_flow: Boolean
)

data class Tag(
    val _content: String,
    val author: String,
    val authorname: String,
    val id: String,
    val machine_tag: Int,
    val raw: String
)

data class Url(
    val _content: String,
    val type: String
)