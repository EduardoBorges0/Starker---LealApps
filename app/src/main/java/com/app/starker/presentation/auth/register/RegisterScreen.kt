package com.app.starker.presentation.auth.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import com.app.starker.presentation.common.view.CustomTextField
import com.app.starker.presentation.common.view.LoadingOverview
import com.app.starker.presentation.navigation.routes.auth.AuthRoutes
import com.app.starker.presentation.navigation.routes.workout.WorkoutRoutes

@Composable
fun RegisterScreen(navHostController: NavHostController, registerViewModel: RegisterViewModel) {
    var usernameField by remember { mutableStateOf("") }
    var emailField by remember { mutableStateOf("") }
    var passwordField by remember { mutableStateOf("") }
    var isEmptyField by remember { mutableStateOf(false) }
    val isBadFormatted by registerViewModel.isBadFormatted.collectAsState()
    val isFirebaseColision by registerViewModel.isFirebaseColision.collectAsState()
    val isError by registerViewModel.isError.collectAsState()
    val isLoading by registerViewModel.isLoading.collectAsState()
    val isNavigate by registerViewModel.isNavigate.collectAsState()
    LaunchedEffect(isNavigate) {
        if (isNavigate) {
            navHostController.navigate(WorkoutRoutes.MainWorkout.route) {
                popUpTo(AuthRoutes.RegisterScreen.route) { inclusive = true }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .height(200.dp)
                .align(Alignment.TopCenter)
        ) {
            Text("Cadastrar", fontSize = 30.sp, modifier = Modifier.align(Alignment.BottomCenter))
        }
        Column(modifier = Modifier.align(Alignment.Center)) {
            CustomTextField(
                value = usernameField,
                onValueChange = {
                    usernameField = it
                },
                placeholder = "Username"
            )
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
            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = {
                    isEmptyField =
                        usernameField.isEmpty() || emailField.isEmpty() || passwordField.isEmpty()
                    if (!isEmptyField) {
                        registerViewModel.registerUser(usernameField, emailField, passwordField)
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
                Text("Registrar")
            }
        }
        Text(
            "Já tem conta? Entre por aqui",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 70.dp)
                .clickable {
                    navHostController.navigate(AuthRoutes.LoginScreen.route)
                }
        )
        if (isLoading) {
            LoadingOverview()
        }
        AlertDialogRegister(
            isBadFormatted = isBadFormatted,
            onBadFormatted = {
                registerViewModel.setBadFormatted(it)
            },
            isFirebaseAuthColision = isFirebaseColision,
            onFirebaseAuthColision = {
                registerViewModel.setFirebaseCollision(it)
            },
            isError = isError,
            onError = {
                registerViewModel.setError(it)
            },
            isEmptyField = isEmptyField,
            onEmptyField = {
                isEmptyField = it
            }
        )
    }
}