package aura.projects.data.dataservice.sqlservice

import androidx.room.Database
import androidx.room.RoomDatabase
import aura.projects.data.model.response.PostCardListResponse
import aura.projects.data.model.sql.ImageEntity


@Database(
    entities = [ImageEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase:RoomDatabase() {
    abstract fun imagesDao(): ImagesDao
}