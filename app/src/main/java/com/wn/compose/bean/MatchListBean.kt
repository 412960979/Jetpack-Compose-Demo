package com.wn.compose.bean

/**
 * 比赛列表
 */
data class MatchListBean(
    val matchType: String = "英超",
    val matchTime: String = "11:47",
    val teamA_name: String = "利物浦",
    val teamA_score: String = "1",
    val teamA_ban: String = "0",
    val teamA_dian: String = "0",
    val teamB_name: String = "英国",
    val teamB_score: String = "3",
    val teamB_ban: String = "0",
    val teamB_dian: String = "4",
)