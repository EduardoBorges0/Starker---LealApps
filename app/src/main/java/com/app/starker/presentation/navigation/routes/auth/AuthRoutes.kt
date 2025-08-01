package com.app.starker.presentation.navigation.routes.auth

sealed class AuthRoutes(
    val route: String
) {
    object RegisterScreen : AuthRoutes("register_screen")
    object LoginScreen : AuthRoutes("login_screen")
}