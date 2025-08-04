package com.app.starker.presentation.workouts.showWorkout.showWorkoutDetails

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.app.starker.R
import com.app.starker.presentation.common.view.AlertDialogComposable

@Composable
fun AlertDialogWorkoutDetails(
    isDeleteWorkout: Boolean,
    onDeleteWorkout: () -> Unit,
    hasError: Boolean,
    onHasError: (Boolean) -> Unit
) {
    if(isDeleteWorkout){
        AlertDialogComposable(
            onConfirm = { onDeleteWorkout() },
            onDismiss = {  },
            title = "Excluir",
            message = "Você deseja deletar esse treino?",
            confirmButtonText = "Confirmar"
        )
    }
    if(hasError){
        AlertDialogComposable(
            onConfirm = { onHasError(false) },
            onDismiss = {  },
            title = "Erro",
            message = "Aconteceu algum erro",
            confirmButtonText = "Ok"
        )
    }
}