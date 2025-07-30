package com.app.starker.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [
    FirebaseModule::class,
    RepositoriesModule::class
])
@InstallIn(SingletonComponent::class)
object AppModule