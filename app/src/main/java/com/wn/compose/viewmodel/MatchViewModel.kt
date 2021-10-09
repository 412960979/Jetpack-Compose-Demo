package com.wn.compose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.wn.compose.bean.MatchListBean
import com.wn.compose.paging.repository.MatchRepository
import com.wn.compose.paging.source.MatchSource
import kotlinx.coroutines.flow.Flow

class MatchViewModel(repository: MatchRepository) : ViewModel() {
    val matchListData: Flow<PagingData<MatchListBean>> = Pager(config = PagingConfig(pageSize = 20)) {
            MatchSource(repository = repository)
        }.flow
}