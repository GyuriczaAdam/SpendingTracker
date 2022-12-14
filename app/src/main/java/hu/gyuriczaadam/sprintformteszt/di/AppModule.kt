package hu.gyuriczaadam.sprintformteszt.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.gyuriczaadam.sprintformteszt.BuildConfig
import hu.gyuriczaadam.sprintformteszt.data.local.data_source.TransactionsDatabase
import hu.gyuriczaadam.sprintformteszt.data.remote.TransactionApi
import hu.gyuriczaadam.sprintformteszt.data.repository.TransactionRepositoryImpl
import hu.gyuriczaadam.sprintformteszt.domain.repositories.TransactionRepository
import hu.gyuriczaadam.sprintformteszt.domain.use_case.*
import hu.gyuriczaadam.sprintformteszt.domain.use_case.get_sum_of_transactions_use_cases.GetAllTransactionsUseCase
import hu.gyuriczaadam.sprintformteszt.domain.use_case.get_sum_of_transactions_use_cases.GetMaxTransactionUseCase
import hu.gyuriczaadam.sprintformteszt.domain.use_case.get_sum_of_transactions_use_cases.GetSumOfTransactionsByQuery
import hu.gyuriczaadam.sprintformteszt.domain.use_case.get_sum_of_transactions_use_cases.SumOfTransactionsUseCase
import hu.gyuriczaadam.sprintformteszt.domain.use_case.get_transactions_use_cases.GetTransactionByIdUseCase
import hu.gyuriczaadam.sprintformteszt.domain.use_case.get_transactions_use_cases.GetTransactionByQueryUseCase
import hu.gyuriczaadam.sprintformteszt.domain.use_case.get_transactions_use_cases.GetTransactionsFromApiUseCase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTransactionApi():TransactionApi{
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
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
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideTransactionUseCases(repository: TransactionRepository):TransactionUseCases{
        return TransactionUseCases(
            getAllTransactionsUseCase = GetAllTransactionsUseCase(repository),
            getTransactionByIdUseCase = GetTransactionByIdUseCase(repository),
            getTransactionByQueryUseCase = GetTransactionByQueryUseCase(repository),
            getTransactionsFromApiUseCase = GetTransactionsFromApiUseCase(repository),
            insertTransactionUseCase = InsertTransactionUseCase(repository),
            transactionTypesListUseCase = TransactionTypesListUseCase(),
            sumOfTransactionsUseCase = SumOfTransactionsUseCase(repository),
            getSumOfTransactionsByQuery = GetSumOfTransactionsByQuery(repository),
            getMaxTransactionUseCase = GetMaxTransactionUseCase(repository),
        )
    }

}