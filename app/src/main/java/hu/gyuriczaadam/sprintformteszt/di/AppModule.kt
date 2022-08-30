package hu.gyuriczaadam.sprintformteszt.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.gyuriczaadam.sprintformteszt.data.local.daos.TransactionDao
import hu.gyuriczaadam.sprintformteszt.data.local.data_source.TransactionsDatabase
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
    fun provideTransactionRepository(
        api:TransactionApi,
        db:TransactionsDatabase
    ):TransactionRepository{
        return TransactionRepositoryImpl(
            transactionApi = api,
            transactionDao = db.transactionDao
        )
    }

    @Provides
    @Singleton
    fun provideTransactionDatabase(app:Application):TransactionsDatabase{
        return Room.databaseBuilder(app,
            TransactionsDatabase::class.java,
            "transaction_db"
            ).build()
    }
}