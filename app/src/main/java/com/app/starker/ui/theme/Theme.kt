package com.app.starker.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = GoldButton,              // botões principais
    onPrimary = BlackBackground,       // texto dentro do botão
    secondary = WhiteText,             // cor secundária (ex: FAB)
    onSecondary = BlackBackground,     // texto dentro do FAB

    background = BlackBackground,      // fundo da tela
    onBackground = WhiteText,          // texto sobre o fundo

    // texto sobre cards/surface
)

private val LightColorScheme = lightColorScheme(
    primary = GoldButton,
    onPrimary = BlackBackground,

    secondary = WhiteText,
    onSecondary = BlackBackground,

    background = BlackBackground,
    onBackground = WhiteText,

)


@Composable
fun StarkerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(), // não faz diferença aqui
    content: @Composable () -> Unit
) {
    val colorScheme = DarkColorScheme // ou LightColorScheme, tanto faz

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
