package com.app.starker.presentation.auth.register

import androidx.compose.runtime.Composable
import com.app.starker.presentation.common.view.AlertDialogComposable

@Composable
fun AlertDialogRegister(
    isFirebaseAuthColision: Boolean,
    onFirebaseAuthColision: (Boolean) -> Unit,
    isBadFormatted: Boolean,
    onBadFormatted: (Boolean) -> Unit,
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
    if(isBadFormatted){
        AlertDialogComposable(
            onConfirm = {
                onBadFormatted(false)
            },
            onDismiss = {
                onBadFormatted(false)
            },
            title = "Erro no email",
            message = "Email mal formatado!!"
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
    if(isFirebaseAuthColision){
        AlertDialogComposable(
            onConfirm = {
                onFirebaseAuthColision(false)
            },
            onDismiss = {
                onFirebaseAuthColision(false)
            },
            title = "Error",
            message = "Esse email já existe!!"
        )
    }
}