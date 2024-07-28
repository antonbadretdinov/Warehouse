package com.example.warehouse.ui.screens.dialogs

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.warehouse.R
import com.example.warehouse.ui.theme.dialogContainer

@Composable
fun DeleteDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {

    AlertDialog(
        containerColor = dialogContainer,
        onDismissRequest = { onDismiss() },
        icon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_warning),
                contentDescription = stringResource(
                    R.string.warning_icon_description
                )
            )
        },
        confirmButton = {
            TextButton(onClick = { onConfirm() }) {
                Text(text = stringResource(R.string.Yes), fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(text = stringResource(R.string.No), fontWeight = FontWeight.Bold)
            }
        },
        title = {
            Text(text = stringResource(R.string.delete_item_dialog_title))
        },
        text = {
            Text(text = stringResource(R.string.delete_item_dialog_text))
        }
    )


}