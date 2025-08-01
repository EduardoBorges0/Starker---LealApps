package com.app.starker.presentation.auth.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.app.starker.presentation.auth.register.AlertDialogRegister
import com.app.starker.presentation.common.view.CustomTextField
import com.app.starker.presentation.common.view.LoadingOverview
import com.app.starker.presentation.navigation.routes.auth.AuthRoutes
import com.app.starker.presentation.navigation.routes.workout.WorkoutRoutes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navHostController: NavHostController, loginViewModel: LoginViewModel) {
    var emailField by remember { mutableStateOf("") }
    var passwordField by remember { mutableStateOf("") }
    var isEmptyField by remember { mutableStateOf(false) }
    val isLoading by loginViewModel.isLoading.collectAsState()
    val isAuthInvalid by loginViewModel.isAuthInvalid.collectAsState()
    val isError by loginViewModel.isError.collectAsState()
    val isNavigate by loginViewModel.isNavigate.collectAsState()
    LaunchedEffect(isNavigate) {
        if (isNavigate) {
            navHostController.navigate(WorkoutRoutes.MainWorkout.route) {
                popUpTo(AuthRoutes.RegisterScreen.route) { inclusive = true }
            }
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = {},
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background
            ),
            navigationIcon = {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Voltar",
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .clickable {
                            navHostController.popBackStack()
                        }
                )
            },
            modifier = Modifier.align(Alignment.TopStart)
        )
        Box(
            modifier = Modifier
                .height(200.dp)
                .align(Alignment.TopCenter)
        ) {
            Text("Entrar", fontSize = 30.sp, modifier = Modifier.align(Alignment.BottomCenter))
        }
        Column(modifier = Modifier.align(Alignment.Center)) {
            Spacer(modifier = Modifier.height(20.dp))
            CustomTextField(
                value = emailField,
                onValueChange = {
                    emailField = it
                },
                placeholder = "Email"
            )
            Spacer(modifier = Modifier.height(20.dp))
            CustomTextField(
                value = passwordField,
                onValueChange = {
                    passwordField = it
                },
                placeholder = "Senha"
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text("Esqueceu a senha?", modifier = Modifier.padding(horizontal = 40.dp))
            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = {
                    isEmptyField =
                        emailField.isEmpty() || passwordField.isEmpty()
                    if (!isEmptyField) {
                        loginViewModel.loginUser(emailField, passwordField)
                    }
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(horizontal = 40.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.secondary
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Entrar")
            }
        }
        if (isLoading) {
            LoadingOverview()
        }
        AlertDialogLogin(
           isAuthInvalid = isAuthInvalid,
           onAuthInvalid = {
               loginViewModel.setAuthInvalid(it)
           },
            isEmptyField = isEmptyField,
            onEmptyField = {
                isEmptyField = false
            },
            isError = isError,
            onError =  {
                loginViewModel.setIsError(it)
            }
        )
    }

}