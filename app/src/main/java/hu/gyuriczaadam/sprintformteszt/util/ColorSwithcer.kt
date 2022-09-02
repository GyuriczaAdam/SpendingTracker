package hu.gyuriczaadam.sprintformteszt.util

import androidx.compose.ui.graphics.Color

fun switchColor(sum:Long): Color {
    when(true){
        (sum<10000)-> return Color.Green
        (sum in 10001..49999)->return Color.Yellow
        (sum in 50000..200000)->return Color.Red
        else->return Color.Cyan
    }
}
