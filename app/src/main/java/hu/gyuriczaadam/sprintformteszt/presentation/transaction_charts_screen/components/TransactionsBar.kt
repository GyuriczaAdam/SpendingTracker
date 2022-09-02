package hu.gyuriczaadam.sprintformteszt.presentation.add_custom_transaction_screen.components

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Canvas
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import hu.gyuriczaadam.sprintformteszt.util.switchColor

@Composable
fun TransactionsBar(
   transactionAmount:Long,
    maxAmount:Long,
    modifier: Modifier = Modifier,
){
    val background = Color.LightGray
    val maxAmountExeededColor = MaterialTheme.colors.error

    val transactionAmountWidthRatio = remember {
        Animatable(0f)
    }


    LaunchedEffect(key1 =transactionAmount){
        transactionAmountWidthRatio.animateTo(
            targetValue = ( transactionAmount / maxAmount.toFloat() )
        )
    }


    Canvas(modifier = modifier ){
        if(transactionAmount <= maxAmount){
            val transactionAmountWidth = transactionAmountWidthRatio.value * size.width
            drawRoundRect(
                color = background,
                size = size,
                cornerRadius = CornerRadius(100f)
            )
            drawRoundRect(
                color = switchColor(transactionAmount),
                size = Size(
                    width = transactionAmountWidth,
                    height = size.height
                ),
                cornerRadius = CornerRadius(100f)
            )

        }else{
            drawRoundRect(
                color = maxAmountExeededColor,
                size= size,
                cornerRadius = CornerRadius(100f)
            )
        }
    }
}

