package hu.gyuriczaadam.sprintformteszt.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.gyuriczaadam.sprintformteszt.data.remote.TransactionApi
import hu.gyuriczaadam.sprintformteszt.data.repository.TransactionRepositoryImpl
import hu.gyuriczaadam.sprintformteszt.domain.repositories.TransactionRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //TODO:Put baseurl to buildsrc
    @Provides
    @Singleton
    fun provideTransactionApi():TransactionApi{
        return Retrofit.Builder()
            .baseUrl("https://development.sprintform.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TransactionApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTransactionRepository(api:TransactionApi):TransactionRepository{
        return TransactionRepositoryImpl(api)
    }
}