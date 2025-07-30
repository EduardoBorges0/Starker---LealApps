package com.app.starker.di

import com.app.starker.data.network.firebase.AuthNetwork
import com.app.starker.data.network.firebase.ExerciseNetwork
import com.app.starker.data.network.firebase.WorkoutNetwork
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {
    @Provides
    @Singleton
    fun providerAuthNetwork() : AuthNetwork{
        return AuthNetwork()
    }
    @Provides
    @Singleton
    fun providerExerciseNetwork() : ExerciseNetwork{
        return ExerciseNetwork()
    }
    @Provides
    @Singleton
    fun providerWorkoutNetwork() : WorkoutNetwork{
        return WorkoutNetwork()
    }
}