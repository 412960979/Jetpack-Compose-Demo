package com.wn.compose.paging.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.wn.compose.bean.MatchListBean
import com.wn.compose.net.NetResult
import com.wn.compose.paging.repository.MatchRepository

class MatchSource(private val repository: MatchRepository) : PagingSource<Int, MatchListBean>() {
    override fun getRefreshKey(state: PagingState<Int, MatchListBean>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MatchListBean> {
        val nextPage = params.key ?: 1
        val resultResponse = repository.getMatchListData(nextPage)

        return try {
            if (resultResponse.code == NetResult.SUCCESS) {
                return LoadResult.Page(
                    data = resultResponse.data ?: mutableListOf(),
                    prevKey = if (nextPage == 1) null else nextPage - 1,
                    nextKey = if (nextPage > 3) null else nextPage + 1// 这里假设大于3就没有下一页
                )
            } else {
                LoadResult.Error(throwable = Exception())
            }
        } catch (e: Exception) {
            LoadResult.Error(throwable = Exception())
        }
    }

}