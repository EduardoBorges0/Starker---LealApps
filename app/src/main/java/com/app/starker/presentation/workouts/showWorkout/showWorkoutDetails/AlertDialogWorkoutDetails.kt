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
            title = stringResource(R.string.remove_workout),
            message = stringResource(R.string.do_you_wish_remove_thats_workout),
            confirmButtonText = stringResource(R.string.confirm_button_remove_workout)
        )
    }
    if(hasError){
        AlertDialogComposable(
            onConfirm = { onHasError(false) },
            onDismiss = {  },
            title = stringResource(R.string.workout_details_error),
            message = stringResource(R.string.workout_details_text_error),
            confirmButtonText = stringResource(R.string.confirm_button_erro_workout_details)
        )
    }
}