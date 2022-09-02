package hu.gyuriczaadam.sprintformteszt.presentation.add_custom_transaction_screen.components

import android.content.Context
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
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
) {
    OutlinedTextField(
    value = text,
    onValueChange = onValueChange,
    singleLine = singleLine,
    textStyle = textStyle,
    label={
        Text(text = hint.asString(context))
    },
    leadingIcon={
        Icon(imageVector =imageVector , contentDescription = stringResource(R.string.textfiled_icon_text))
    },
    keyboardOptions=keyboardOptions,
    modifier = Modifier

        .fillMaxWidth()
        .onFocusChanged {
            onFocusChange(it)
        }
    )
}