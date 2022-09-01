package hu.gyuriczaadam.sprintformteszt.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hu.gyuriczaadam.sprintformteszt.data.local.entities.TransactionListEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.toList

@Dao
interface TransactionDao {
    @Query("SELECT * FROM transactionlistentity")
    fun getTransactions():Flow<List<TransactionListEntity>>

    @Query("SELECT * FROM transactionlistentity WHERE summary  LIKE '%'||:query||'%' OR sum LIKE '%'||:query||'%' OR category LIKE '%'||:query||'%' ")
    fun getTransactionsByQuery(query:String):Flow<List<TransactionListEntity>>


    @Query("SELECT * FROM transactionlistentity WHERE id = :query")
    suspend fun getTransactionById(query: Int):TransactionListEntity?

    @Query("SELECT * FROM transactionlistentity WHERE id = :query")
    suspend fun getTransactionsById(query: Int):List<TransactionListEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transactionListEntity: TransactionListEntity)

    @Query("SELECT COALESCE(SUM(sum),0) FROM transactionlistentity")
    suspend fun getSumOfTransactions():Long?

    @Query("SELECT COALESCE(SUM(sum),0) FROM transactionlistentity WHERE  summary LIKE '%'||:query||'%' OR sum LIKE '%'||:query||'%' OR category LIKE '%'||:query||'%'")
    suspend fun getSumOfTransactionsByQuery(query: String):Long?

    suspend fun insertOrIgnoreTransaction(transactionListEntity: TransactionListEntity) {
       val transactionsFromDb:List<TransactionListEntity>  = getTransactionsById(transactionListEntity.id!!)
        if(transactionsFromDb.isEmpty()){
            insertTransaction(transactionListEntity)
        }
    }
}