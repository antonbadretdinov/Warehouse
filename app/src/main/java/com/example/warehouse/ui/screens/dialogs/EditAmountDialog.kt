package com.example.warehouse.ui.screens.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.warehouse.R
import com.example.warehouse.ui.theme.dialogContainer

@Composable
fun EditAmountDialog(
    amount: Int,
    onDismiss: () -> Unit,
    onConfirm: (amount: Int) -> Unit
) {

    var amountState by remember {
        mutableIntStateOf(amount)
    }

    AlertDialog(
        containerColor = dialogContainer,
        onDismissRequest = { onDismiss() },
        icon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_settings),
                contentDescription = stringResource(R.string.settings_icon_dialog)

            )
        },
        confirmButton = {
            TextButton(onClick = { onConfirm(amountState) }) {
                Text(text = stringResource(R.string.confirm), fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(text = stringResource(R.string.cancel), fontWeight = FontWeight.Bold)
            }
        },
        title = {
            Text(text = stringResource(R.string.edit_item_dialog_title))
        },
        text = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        if (amountState > 0) {
                            amountState--
                        }
                    }
                ) {
                    Icon(
                        modifier = Modifier.size(32.dp),
                        painter = painterResource(id = R.drawable.ic_minus),
                        contentDescription = stringResource(
                            R.string.minus_icon_for_edit_dialog
                        ),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }

                Text(
                    text = amountState.toString(),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Normal
                )

                IconButton(
                    onClick = {
                        amountState++
                    }
                ) {
                    Icon(
                        modifier = Modifier.size(32.dp),
                        painter = painterResource(id = R.drawable.ic_plus),
                        contentDescription = stringResource(
                            R.string.plus_icon_for_edit_dialog
                        ),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    )
}