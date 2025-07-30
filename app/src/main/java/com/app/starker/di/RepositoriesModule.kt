package com.app.starker.di

import com.app.starker.data.network.firebase.AuthNetwork
import com.app.starker.data.network.firebase.WorkoutNetwork
import com.app.starker.data.repositoriesImpl.AuthRepositoriesImpl
import com.app.starker.data.repositoriesImpl.ExerciseRepositoriesImpl
import com.app.starker.data.repositoriesImpl.WorkoutRepositoriesImpl
import com.app.starker.domain.repositories.AuthRepositories
import com.app.starker.domain.repositories.ExerciseRepositories
import com.app.starker.domain.repositories.WorkoutRepositories
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {
    @Provides
    @Singleton
    fun providerAuthRepositories(authNetwork: AuthNetwork): AuthRepositories {
        return AuthRepositoriesImpl(authNetwork);
    }

    @Provides
    @Singleton
    fun providerWorkoutRepositories(workoutNetwork: WorkoutNetwork): WorkoutRepositories {
        return WorkoutRepositoriesImpl(workoutNetwork);
    }

    @Provides
    @Singleton
    fun providerExerciseRepositories(): ExerciseRepositories {
        return ExerciseRepositoriesImpl();
    }
}