
package com.noahjutz.gymroutines.ui.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.noahjutz.gymroutines.R
import com.noahjutz.gymroutines.ui.components.TopBar

@ExperimentalMaterialApi
@Composable
fun AppSettings(
    popBackStack: () -> Unit,
    navToAbout: () -> Unit,
    navToAppearanceSettings: () -> Unit,
    navToDataSettings: () -> Unit,
    navToGeneralSettings: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(R.string.screen_settings),
                navigationIcon = {
                    IconButton(onClick = popBackStack) {
                        Icon(Icons.Default.ArrowBack, stringResource(R.string.btn_pop_back))
                    }
                }
            )
        }
    ) {
        Column(
            Modifier.scrollable(
                orientation = Orientation.Vertical,
                state = rememberScrollState()
            )
        ) {
            ListItem(
                modifier = Modifier.clickable(onClick = navToGeneralSettings),
                text = { Text(stringResource(R.string.screen_general_settings)) },
                icon = { Icon(Icons.Default.Construction, null) }
            )
            ListItem(
                modifier = Modifier.clickable(onClick = navToAppearanceSettings),
                text = { Text(stringResource(R.string.screen_appearance_settings)) },
                icon = { Icon(Icons.Default.DarkMode, null) }
            )
            ListItem(
                modifier = Modifier.clickable(onClick = navToDataSettings),
                text = { Text(stringResource(R.string.screen_data_settings)) },
                icon = { Icon(Icons.Default.Shield, null) },
            )
            Divider()
            ListItem(
                modifier = Modifier.clickable(onClick = navToAbout),
                text = { Text(stringResource(R.string.screen_about)) },
                icon = { Icon(Icons.Default.Info, null) }
            )
        }
    }
}
