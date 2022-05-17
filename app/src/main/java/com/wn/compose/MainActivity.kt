package com.wn.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.wn.compose.bean.MatchListBean
import com.wn.compose.ui.chat.MatchDetailPage
import com.wn.compose.ui.home.HomePage
import com.wn.compose.viewmodel.MatchViewModel

@ExperimentalComposeUiApi
@ExperimentalUnitApi
@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {
    private val matchViewModel: MatchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            MaterialTheme {
                // 为了支持沉浸式状态栏，还可支持弹出输入法布局上移
                ProvideWindowInsets {
                    // 系统UI控制器
                    val systemUiController = rememberSystemUiController()
                    SideEffect {
                        // 状态栏
                        systemUiController.setSystemBarsColor(color = Color.Transparent,
                            darkIcons = false)
                        // 底部导航栏
                        systemUiController.setNavigationBarColor(color = Color.White,
                            darkIcons = false)
                    }

                    // 赛事列表，这个只需要执行一次，否则切换页面会刷新
                    lazyMovieItems = matchViewModel!!.matchListData.collectAsLazyPagingItems()

                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "home"
                    ){
                        composable("home"){ HomePage(this@MainActivity, navController) }
                        composable("match_detail"){ MatchDetailPage(navController) }
                    }
                }
            }
        }
    }


}

var lazyMovieItems: LazyPagingItems<MatchListBean>? = null
