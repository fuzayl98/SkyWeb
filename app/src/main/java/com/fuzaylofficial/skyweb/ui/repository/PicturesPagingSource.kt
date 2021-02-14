package com.fuzaylofficial.skyweb.ui.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fuzaylofficial.skyweb.Constants.DEFAULT_PAGE_INDEX
import com.fuzaylofficial.skyweb.model.Picture
import retrofit2.HttpException
import java.io.IOException

class PicturesPagingSource(private val service: PicturesService) : PagingSource<Int,Picture>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Picture> {

        val page = params.key ?: DEFAULT_PAGE_INDEX
        return try {
            val response = service.getImages(page, params.loadSize)
            LoadResult.Page(
                    response, prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1,
                    nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Picture>): Int? {
        return state?.anchorPosition
    }
}