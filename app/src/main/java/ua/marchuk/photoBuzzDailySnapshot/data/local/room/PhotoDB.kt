package ua.marchuk.photoBuzzDailySnapshot.data.local.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "photo")
data class PhotoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val url: String,
    val title: String,
    val timestamp : Long,
)

@Entity(tableName = "photo_description",
    foreignKeys = [ForeignKey(
        entity = PhotoEntity::class,
        parentColumns = ["id"],
        childColumns = ["id"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class PhotoDescriptionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val description: String
)
