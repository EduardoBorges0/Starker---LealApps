package com.app.starker.data.network.firebase

import android.net.Uri
import com.app.starker.data.model.ExerciseModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await

class ExerciseNetwork {
    private val firestoreInstance = FirebaseFirestore.getInstance()
    private val uid = FirebaseAuth.getInstance().uid ?: ""
    private val storageInstance = FirebaseStorage.getInstance()

    // Cria um exercício na subcoleção "Exercises" de um Workout
    suspend fun createExercise(workoutId: String, exercise: ExerciseModel): String? {
        val docRef = firestoreInstance
            .collection("Users").document(uid)
            .collection("Workout").document(workoutId)
            .collection("Exercises").document()
        val exerciseWithId = exercise.copy(id = docRef.id)
        docRef.set(exerciseWithId).await()
        return docRef.id
    }

    // Deleta um exercício da subcoleção "Exercises" de um Workout
    suspend fun deleteExercise(workoutId: String, exerciseId: String) {
        firestoreInstance
            .collection("Users").document(uid)
            .collection("Workout").document(workoutId)
            .collection("Exercises").document(exerciseId)
            .delete().await()
    }

    // Atualiza um exercício na subcoleção "Exercises" de um Workout
    suspend fun updateExercise(workoutId: String, exercise: ExerciseModel) {
        firestoreInstance
            .collection("Users").document(uid)
            .collection("Workout").document(workoutId)
            .collection("Exercises").document(exercise.id)
            .set(exercise).await()
    }

    // Busca todos os exercícios de um Workout
    suspend fun getEveryExercise(workoutId: String): List<ExerciseModel> {
        val snapshot = firestoreInstance
            .collection("Users").document(uid)
            .collection("Workout").document(workoutId)
            .collection("Exercises")
            .get().await()
        return snapshot.toObjects(ExerciseModel::class.java)
    }

    // Busca um exercício pelo id
    suspend fun getExerciseById(workoutId: String, exerciseId: String): ExerciseModel? {
        val doc = firestoreInstance
            .collection("Users").document(uid)
            .collection("Workout").document(workoutId)
            .collection("Exercises").document(exerciseId)
            .get().await()
        return doc.toObject(ExerciseModel::class.java)
    }

    // Faz upload da imagem do exercício para o Firebase Storage e retorna a URL
    suspend fun uploadExerciseImage(workoutId: String, exerciseId: String, imageUri: Uri): String {
        val storageRef = storageInstance.reference
            .child("$uid/$workoutId/$exerciseId")
        storageRef.putFile(imageUri).await()
        return storageRef.downloadUrl.await().toString()
    }

    // Deleta todos os exercícios da subcoleção "Exercises" de um Workout
    suspend fun deleteAllExercises(workoutId: String) {
        val exercisesRef = firestoreInstance
            .collection("Users").document(uid)
            .collection("Workout").document(workoutId)
            .collection("Exercises")
        val snapshot = exercisesRef.get().await()
        snapshot.documents.forEach { it.reference.delete().await() }
    }

    // Deleta a imagem de um exercício específico no Storage (uid/workoutId/exerciseId)
    suspend fun deleteExerciseImage(workoutId: String, exerciseId: String) {
        val storageRef = storageInstance.reference.child("$uid/$workoutId/$exerciseId")
        storageRef.delete().await()
    }

    // Deleta todas as imagens da pasta de um workout no Storage (uid/workoutId)
    suspend fun deleteAllImagesFromWorkout(workoutId: String) {
        val workoutFolderRef = storageInstance.reference.child("$uid/$workoutId")
        val items = workoutFolderRef.listAll().await()
        items.items.forEach { it.delete().await() }
    }
}
