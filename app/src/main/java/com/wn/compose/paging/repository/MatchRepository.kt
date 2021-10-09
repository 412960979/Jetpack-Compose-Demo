package com.wn.compose.paging.repository

import com.wn.compose.api.Api
import com.wn.compose.bean.MatchListBean
import com.wn.compose.net.NetResult
import kotlinx.coroutines.*

class MatchRepository(private val api: Api) {
//    suspend fun getPopularMovies(pageNumber: Int) =
//        api.getPopularMovies(pageNumber)

    suspend fun getMatchListData(pageNumber: Int = 1): NetResult<MutableList<MatchListBean>> {
        var list: MutableList<MatchListBean> = mutableListOf()

        val result = GlobalScope.async {
            when (pageNumber) {
                1 -> {
                    for (i in 0..10){
                        list.add(MatchListBean(matchType = "英超$i"))
                    }
                }
                2 -> {
                    for (i in 11..21){
                        list.add(MatchListBean(matchType = "中超$i"))
                    }
                }
                3 -> {
                    for (i in 22..32){
                        list.add(MatchListBean(matchType = "曼联$i"))
                    }
                }
            }
            NetResult(200, list, "成功")
        }

        delay(1500)
        return result.getCompleted()
    }
}