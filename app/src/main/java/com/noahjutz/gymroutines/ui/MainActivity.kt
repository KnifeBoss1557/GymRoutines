

package com.noahjutz.gymroutines.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.noahjutz.gymroutines.ui.main.MainScreen
import kotlin.time.ExperimentalTime

class MainActivity : AppCompatActivity() {

    @OptIn(
        ExperimentalTime::class, ExperimentalFoundationApi::class,
        ExperimentalMaterialApi::class, ExperimentalAnimationApi::class
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        setContent {
            MainScreen()
        }
    }
}
