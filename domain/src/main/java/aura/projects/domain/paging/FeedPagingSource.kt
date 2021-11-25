package aura.projects.domain.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import aura.projects.core.network.ActionResult
import aura.projects.data.datastore.FeedRepository
import aura.projects.data.model.response.PostCardResponse
import aura.projects.domain.model.Image
import aura.projects.domain.util.mapper.ImageMapper
import aura.projects.domain.util.mapper.ListMapper
import aura.projects.domain.util.mapper.PostCardMapper
import java.lang.Exception

class FeedPagingSource(
    private val feedRepository: FeedRepository
): PagingSource<Int, Image>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Image> {
        return try {
            when(val data = feedRepository.getImages(params.key?:1)){
                is ActionResult.Success -> run {
                    LoadResult.Page(
                        data = ListMapper(ImageMapper).invoke(ListMapper(PostCardMapper).invoke(data.data?.postCard)),
                        prevKey = if (params.key == 1) null else -1,
                        nextKey = params.key?.plus(1)
                    )
                }
                is ActionResult.Error -> {
                    LoadResult.Error(data.errors)
                }
            }
        }catch (e: Exception){
            Log.d("ERRORMESSAGE", e.message.toString())
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, Image>): Int? {
        return null
    }
}