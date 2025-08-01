package com.app.starker.presentation.auth.register

import androidx.compose.runtime.Composable
import com.app.starker.presentation.common.view.AlertDialogComposable
import androidx.compose.ui.res.stringResource
import com.app.starker.R
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
    if (isError) {
        AlertDialogComposable(
            onConfirm = { onError(false) },
            onDismiss = { onError(false) },
            title = stringResource(R.string.error_title),
            message = stringResource(R.string.error_generic)
        )
    }
    if (isBadFormatted) {
        AlertDialogComposable(
            onConfirm = { onBadFormatted(false) },
            onDismiss = { onBadFormatted(false) },
            title = stringResource(R.string.error_email_title),
            message = stringResource(R.string.error_email_bad_format)
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
    if (isFirebaseAuthColision) {
        AlertDialogComposable(
            onConfirm = { onFirebaseAuthColision(false) },
            onDismiss = { onFirebaseAuthColision(false) },
            title = stringResource(R.string.error_title),
            message = stringResource(R.string.error_email_exists_msg)
        )
    }
}
