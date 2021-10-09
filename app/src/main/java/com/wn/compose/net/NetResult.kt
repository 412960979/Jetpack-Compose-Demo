package com.wn.compose.net

data class NetResult<out T>(
    val code: Int,
    val data: T?,
    val msg: String
) {
    companion object {
        const val SUCCESS = 200
    }
}