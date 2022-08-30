package hu.gyuriczaadam.sprintformteszt.data.local.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import hu.gyuriczaadam.sprintformteszt.data.local.daos.TransactionDao
import hu.gyuriczaadam.sprintformteszt.domain.model.local.TransactionListEntity
import hu.gyuriczaadam.sprintformteszt.domain.model.remote.TransactionListModel

@Database(
    entities = [TransactionListEntity::class],
    version = 1
)
abstract class TransactionsDatabase :RoomDatabase(){
    abstract val transactionDao:TransactionDao

}