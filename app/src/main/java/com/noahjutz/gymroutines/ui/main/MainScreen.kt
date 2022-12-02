

package com.noahjutz.gymroutines.ui.main

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.noahjutz.gymroutines.data.ColorTheme
import com.noahjutz.gymroutines.ui.NavGraph
import com.noahjutz.gymroutines.ui.Screen
import com.noahjutz.gymroutines.ui.theme.GymRoutinesTheme
import kotlin.time.ExperimentalTime
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterialNavigationApi::class)
@ExperimentalTime
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun MainScreen(viewModel: MainScreenViewModel = getViewModel()) {
    val bottomSheetNavigator = rememberBottomSheetNavigator()
    val navController = rememberAnimatedNavController(bottomSheetNavigator)

    val colorTheme by viewModel.colorTheme.collectAsState(initial = ColorTheme.FollowSystem)
    val isDark = when (colorTheme) {
        ColorTheme.FollowSystem -> isSystemInDarkTheme()
        ColorTheme.White -> false
        ColorTheme.Black -> true
    }
    GymRoutinesTheme(isDark = isDark) {
        Scaffold(
            bottomBar = {
                val navBackStackEntry by navController.currentBackStackEntryAsState()

                val isCurrentDestinationHomeTab = navBackStackEntry
                    ?.destination
                    ?.route in bottomNavItems.map { it.route }

                if (isCurrentDestinationHomeTab) {
                    Surface(elevation = 8.dp) {
                        Column {
                            val currentWorkoutId by viewModel.currentWorkoutId.collectAsState(
                                initial = -1
                            )
                            val navToWorkoutScreen = {
                                navController.navigate("${Screen.workoutInProgress}/$currentWorkoutId")
                            }
                            if (currentWorkoutId >= 0) {
                                WorkoutBottomSheet(navToWorkoutScreen)
                            }

                            val showBottomNavLabels by viewModel.showBottomLabels.collectAsState(
                                initial = true
                            )
                            BottomBar(
                                navController = navController,
                                showLabels = showBottomNavLabels
                            )
                        }
                    }
                }
            }
        ) { paddingValues ->
            Box(Modifier.padding(paddingValues)) {
                NavGraph(navController = navController, bottomSheetNavigator = bottomSheetNavigator)
            }
        }
    }
}
