package hu.gyuriczaadam.sprintformteszt.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hu.gyuriczaadam.sprintformteszt.data.local.entities.TransactionListEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Query("SELECT * FROM transactionlistentity")
    fun getTransactions():Flow<List<TransactionListEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transactionListEntity: TransactionListEntity)
}