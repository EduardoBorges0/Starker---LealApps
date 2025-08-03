//package com.app.starker.presentation.workouts.insertWorkout
//
//import android.net.Uri
//import android.widget.Toast
//import androidx.activity.compose.rememberLauncherForActivityResult
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.automirrored.filled.ArrowBack
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.unit.dp
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.navigation.NavHostController
//import coil.compose.rememberAsyncImagePainter
//import com.app.starker.R
//import com.app.starker.presentation.common.view.LoadingOverview
//import com.app.starker.presentation.navigation.routes.workout.WorkoutRoutes
//import com.yalantis.ucrop.UCrop
//import java.io.File
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun InsertWorkoutImageScreen(
//    navHostController: NavHostController,
//    workoutName: String,
//    workoutDescription: String,
//    date: String
//) {
//    val viewModel: InsertWorkoutViewModel = hiltViewModel()
//    val context = LocalContext.current
//
//    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
//    val isNavigate by viewModel.isNavigate.collectAsState()
//    val isLoading by viewModel.isLoading.collectAsState()
//
//    // Crop launcher
//    val cropImageLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.StartActivityForResult()
//    ) { result ->
//        if (result.resultCode == android.app.Activity.RESULT_OK) {
//            val resultUri = UCrop.getOutput(result.data!!)
//            selectedImageUri = resultUri
//        }
//    }
//
//    // Image picker launcher
//    val imagePickerLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.GetContent()
//    ) { uri: Uri? ->
//        uri?.let {
//            val destinationUri = Uri.fromFile(File(context.cacheDir, "cropped_${System.currentTimeMillis()}.jpg"))
//            val cropIntent = UCrop.of(it, destinationUri)
//                .withAspectRatio(4f, 2f)
//                .withMaxResultSize(70, 1080)
//                .withOptions(getCropOptions())
//                .getIntent(context)
//
//            cropImageLauncher.launch(cropIntent)
//        }
//    }
//
//    LaunchedEffect(isNavigate) {
//        if (isNavigate) {
//            navHostController.navigate(WorkoutRoutes.ShowWorkout.route) {
//                popUpTo(0) { inclusive = true }
//            }
//        }
//    }
//
//    Box(modifier = Modifier.fillMaxSize()) {
//        TopAppBar(
//            title = {},
//            colors = TopAppBarDefaults.topAppBarColors(
//                containerColor = MaterialTheme.colorScheme.background
//            ),
//            navigationIcon = {
//                Icon(
//                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
//                    contentDescription = stringResource(R.string.back_button_description),
//                    modifier = Modifier
//                        .padding(start = 32.dp, top = 32.dp)
//                        .clickable { navHostController.popBackStack() }
//                )
//            },
//            modifier = Modifier.background(Color.Blue)
//        )
//
//        Button(
//            onClick = { imagePickerLauncher.launch("image/*") },
//            shape = RoundedCornerShape(8.dp),
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(70.dp)
//                .padding(horizontal = 40.dp)
//                .align(Alignment.Center)
//        ) {
//            Text("Selecionar Imagem")
//        }
//
//        Button(
//            onClick = {
//                selectedImageUri?.let { uri ->
//                    val fileName = "workout_${System.currentTimeMillis()}"
//                    viewModel.insertWorkout(uri, fileName, workoutName, workoutDescription, date)
//                } ?: Toast.makeText(context, "Selecione uma imagem primeiro", Toast.LENGTH_SHORT).show()
//            },
//            enabled = selectedImageUri != null,
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(90.dp)
//                .align(Alignment.BottomCenter)
//        ) {
//            Text("Salvar Treino")
//        }
//
//        selectedImageUri?.let { uri ->
//            Image(
//                painter = rememberAsyncImagePainter(uri),
//                contentDescription = null,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(200.dp)
//                    .align(Alignment.TopCenter)
//                    .padding(top = 90.dp)
//            )
//        }
//
//        if (isLoading) {
//            LoadingOverview()
//        }
//    }
//}
//private fun getCropOptions(): UCrop.Options {
//    return UCrop.Options().apply {
//        setFreeStyleCropEnabled(false) // impede redimensionamento livre
//        setHideBottomControls(true)    // esconde botões de aspecto e rotação
//        setToolbarTitle("Recortar imagem")
////        setToolbarColor(Color(0xFF6200EE))
////        setStatusBarColor(Color.parseColor("#3700B3"))
////        setActiveControlsWidgetColor(Color.parseColor("#03DAC5"))
//    }
//}
