package hu.gyuriczaadam.sprintformteszt.presentation.add_custom_transaction_screen.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import hu.gyuriczaadam.sprintformteszt.R
import hu.gyuriczaadam.sprintformteszt.util.UIText

@Composable
fun OutlinedTextField(
    text: String,
    hint: UIText,
    imageVector: ImageVector,
    keyboardOptions: KeyboardOptions,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle = TextStyle(),
    singleLine: Boolean = false,
    onFocusChange: (FocusState) -> Unit,
    context: Context
) {      Box(modifier = Modifier
    .fillMaxWidth()
    .background(MaterialTheme.colors.onPrimary,RoundedCornerShape(2.dp))
    .wrapContentSize(Alignment.TopStart)) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.onPrimary, RoundedCornerShape(2.dp))
            .border(1.dp,MaterialTheme.colors.primary)
            .onFocusChanged {
                onFocusChange(it)
            },
        value = text,
        onValueChange = onValueChange,
        singleLine = singleLine,
        textStyle = textStyle,
        label = {
            Text(text = hint.asString(context))
        },
        leadingIcon = {
            Icon(
                imageVector = imageVector,
                contentDescription = stringResource(R.string.textfiled_icon_text)
            )
        },
        keyboardOptions = keyboardOptions,
        )
    }
}