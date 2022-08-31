package hu.gyuriczaadam.sprintformteszt.presentation.add_custom_transaction_screen.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import hu.gyuriczaadam.sprintformteszt.R

@Composable
fun TransparentHintTextField(
    text: String,
    hint: String,
    imageVector: ImageVector,
    keyboardOptions: KeyboardOptions,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle = TextStyle(),
    singleLine: Boolean = false,
    testTag: String = "",
    onFocusChange: (FocusState) -> Unit
) {
        OutlinedTextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = singleLine,
            textStyle = textStyle,
            label={
                Text(text = hint)
            },
            leadingIcon={
                Icon(imageVector =imageVector , contentDescription = stringResource(R.string.textfiled_icon_text))
            },
            keyboardOptions=keyboardOptions,
            modifier = Modifier
                .testTag(testTag)
                .fillMaxWidth()
                .onFocusChanged {
                    onFocusChange(it)
                }
        )

}