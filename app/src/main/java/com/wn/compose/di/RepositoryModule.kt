package com.wn.compose.di

import com.wn.compose.api.Api
import com.wn.compose.paging.repository.MatchRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { createRepository(get()) }
}

fun createRepository(
    api: Api
) : MatchRepository = MatchRepository(api)