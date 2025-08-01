package com.app.starker.presentation.auth.login

import androidx.compose.runtime.Composable
import com.app.starker.presentation.common.view.AlertDialogComposable

@Composable
fun AlertDialogLogin(
    isAuthInvalid: Boolean,
    onAuthInvalid: (Boolean) -> Unit,
    isEmptyField: Boolean,
    onEmptyField: (Boolean) -> Unit,
    isError: Boolean,
    onError: (Boolean) -> Unit
) {
    if(isError){
        AlertDialogComposable(
            onConfirm = {
                onError(false)
            },
            onDismiss = {
                onError(false)
            },
            title = "Error",
            message = "Algo deu errado!!"
        )
    }
    if(isAuthInvalid){
        AlertDialogComposable(
            onConfirm = {
                onAuthInvalid(false)
            },
            onDismiss = {
                onAuthInvalid(false)
            },
            title = "Error",
            message = "Email ou senha não estão corretas!!"
        )
    }
    if(isEmptyField){
        AlertDialogComposable(
            onConfirm = {
                onEmptyField(false)
            },
            onDismiss = {
                onEmptyField(false)
            },
            title = "Campos vazios",
            message = "Preencha todos os campos!!"
        )
    }
}