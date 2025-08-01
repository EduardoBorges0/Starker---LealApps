package com.app.starker.presentation.auth.login

import androidx.compose.runtime.Composable
import com.app.starker.presentation.common.view.AlertDialogComposable
import androidx.compose.ui.res.stringResource
import com.app.starker.R

@Composable
fun AlertDialogLogin(
    isAuthInvalid: Boolean,
    onAuthInvalid: (Boolean) -> Unit,
    isEmptyField: Boolean,
    onEmptyField: (Boolean) -> Unit,
    isError: Boolean,
    onError: (Boolean) -> Unit
) {
    if (isError) {
        AlertDialogComposable(
            onConfirm = { onError(false) },
            onDismiss = { onError(false) },
            title = stringResource(R.string.error_title),
            message = stringResource(R.string.error_generic)
        )
    }
    if (isAuthInvalid) {
        AlertDialogComposable(
            onConfirm = { onAuthInvalid(false) },
            onDismiss = { onAuthInvalid(false) },
            title = stringResource(R.string.error_title),
            message = stringResource(R.string.error_auth_invalid_msg)
        )
    }
    if (isEmptyField) {
        AlertDialogComposable(
            onConfirm = { onEmptyField(false) },
            onDismiss = { onEmptyField(false) },
            title = stringResource(R.string.error_empty_fields_title),
            message = stringResource(R.string.error_empty_fields_msg)
        )
    }
}
