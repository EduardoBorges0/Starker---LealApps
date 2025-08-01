package com.app.starker.presentation.navigation.routes.auth

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.app.starker.presentation.auth.login.LoginScreen
import com.app.starker.presentation.auth.login.LoginViewModel
import com.app.starker.presentation.auth.register.RegisterScreen
import com.app.starker.presentation.auth.register.RegisterViewModel

fun NavGraphBuilder.NavAuthRoutes(navHostController: NavHostController) {
    composable(route = AuthRoutes.RegisterScreen.route) {
        val registerViewModel : RegisterViewModel = hiltViewModel()
        RegisterScreen(navHostController, registerViewModel)
    }
    composable(route = AuthRoutes.LoginScreen.route, enterTransition = {
        slideIntoContainer(
            AnimatedContentTransitionScope.SlideDirection.Up,
            tween(1000)
        )
    }, exitTransition = {
        slideOutOfContainer(
            AnimatedContentTransitionScope.SlideDirection.Down,
            tween(1000)
        )
    }) {
        val loginViewModel : LoginViewModel = hiltViewModel()
        LoginScreen(navHostController, loginViewModel)
    }
}