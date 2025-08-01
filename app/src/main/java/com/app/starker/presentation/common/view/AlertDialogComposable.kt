package com.app.starker.presentation.common.view

import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable

import androidx.compose.material3.*
import androidx.compose.ui.res.stringResource

@Composable
fun AlertDialogComposable(
    onDismiss: () -> Unit,
    title: String = "Título",
    message: String = "Mensagem",
    confirmButtonText: String = "OK",
    dismissButtonText: String = "Cancelar",
    onConfirm: () -> Unit = {},
    onDismissButtonClick: () -> Unit = {}
) {

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = title)
        },
        text = {
            Text(text = message)
        },
        confirmButton = {
            TextButton(onClick = {
                onConfirm()
                onDismiss()
            }) {
                Text(text = confirmButtonText)
            }
        },
        dismissButton = {
            TextButton(onClick = {
                onDismissButtonClick()
                onDismiss()
            }) {
                Text(text = dismissButtonText)
            }
        }
    )

}
