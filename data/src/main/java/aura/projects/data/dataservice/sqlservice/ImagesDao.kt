package aura.projects.data.dataservice.sqlservice

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import aura.projects.data.model.response.PostCardListResponse
import aura.projects.data.model.sql.ImageEntity

@Dao
interface ImagesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(item: List<ImageEntity>)

    @Query("SELECT * FROM images")
    suspend fun getAll(): List<ImageEntity>?

    @Query("DELETE FROM images")
    suspend fun deleteAll()

}