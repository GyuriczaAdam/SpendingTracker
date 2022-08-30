package hu.gyuriczaadam.sprintformteszt.domain.model

import androidx.compose.ui.graphics.vector.ImageVector

data class TransactionItem(
    val summary: String,
    val paid: String,
    val sum: Int,
    val currency: String,
    val id:Int,
    val type:String,
    val imageVector: ImageVector
)
