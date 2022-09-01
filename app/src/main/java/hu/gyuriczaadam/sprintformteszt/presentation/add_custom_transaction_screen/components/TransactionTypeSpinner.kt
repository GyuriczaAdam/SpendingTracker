package hu.gyuriczaadam.sprintformteszt.presentation.add_custom_transaction_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import hu.gyuriczaadam.sprintformteszt.R
import hu.gyuriczaadam.sprintformteszt.presentation.common.LocalSpacing

@Composable
fun TransactionTypeSpinner(
    options:List<String>,
    onValueChange: (String) -> Unit,
    imageVector: ImageVector,
    text:String

    ) {
        val localSpacing = LocalSpacing.current
        var expanded by remember { mutableStateOf(false) }
        var selectedIndex by remember { mutableStateOf(0) }


        Box(modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopStart)) {
            OutlinedButton(onClick = { expanded = true} ) {
                Icon(imageVector = imageVector, contentDescription = stringResource(R.string.textfiled_icon_text), tint = Color.DarkGray)
                Spacer(modifier = Modifier.width(localSpacing.spaceSmall) )
                Text(text = text, color = Color.DarkGray,modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = {
                        expanded = true
                        onValueChange
                    },
                        )
                    .background(
                        MaterialTheme.colors.onPrimary
                    ))
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                options.forEachIndexed { index, s ->
                    DropdownMenuItem(onClick = {
                        selectedIndex = index
                        onValueChange(s)
                        expanded = false
                    }) {
                        Text(text = s, modifier = Modifier.wrapContentSize(), color = Color.DarkGray)
                    }
                }
            }
        }
}