package com.app.starker.presentation.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.app.starker.presentation.common.view.TopBarView
import com.app.starker.presentation.navigation.routes.auth.AuthRoutes

@Composable
fun SettingsScreen(navHostController: NavHostController) {
    val viewModel: SettingsViewModel = hiltViewModel()
    Column {
        TopBarView(navHostController)

        Column(modifier = Modifier.padding(32.dp)) {
            for (i in 0 until SettingsTypes.values().size){
                Spacer(modifier = Modifier.height(32.dp))
                Row(
                    modifier = Modifier.clickable{
                        if(SettingsTypes.values()[i] == SettingsTypes.LOGOUT) {
                            viewModel.logout()
                            navHostController.navigate(AuthRoutes.RegisterScreen.route){
                                popUpTo(0) { inclusive = true }
                            }
                        }
                    }
                ) {
                    Icon(SettingsTypes.values()[i].imageVector, null)
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(stringResource(SettingsTypes.values()[i].description))
                }
                Spacer(modifier = Modifier.height(16.dp))
                Divider()
            }

        }

    }
}