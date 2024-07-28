package com.example.warehouse.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.warehouse.R
import com.example.warehouse.ui.screens.dialogs.DeleteDialog
import com.example.warehouse.ui.screens.dialogs.EditAmountDialog
import com.example.warehouse.ui.viewModels.HomeViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel()
) {

    val itemsUIState = viewModel.itemsStateFlow.collectAsStateWithLifecycle()

    var searchTextState by rememberSaveable {
        mutableStateOf("")
    }

    val focusManager = LocalFocusManager.current

    var showDeleteDialog by remember {
        mutableStateOf(false)
    }

    var showEditDialog by remember {
        mutableStateOf(false)
    }

    var usedIdState by remember {
        mutableIntStateOf(0)
    }

    val interactionSource = remember { MutableInteractionSource() }

    if (showEditDialog) {
        EditAmountDialog(
            amount = itemsUIState.value.find { it.id == usedIdState }!!.amount,
            onConfirm = { newAmount ->
                viewModel.updateItem(
                    itemsUIState.value.find { it.id == usedIdState }!!.copy(amount = newAmount)
                )
                showEditDialog = false
            },
            onDismiss = {
                showEditDialog = false
            }
        )
    }

    if (showDeleteDialog) {
        DeleteDialog(
            onDismiss = {
                showDeleteDialog = false
            },
            onConfirm = {
                viewModel.deleteItem(itemsUIState.value.find { it.id == usedIdState }!!)
                showDeleteDialog = false
            }
        )
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(R.string.items_list_title))
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { topAppBarPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(topAppBarPadding)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    focusManager.clearFocus()
                }
        ) {
            item {
                OutlinedTextField(
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        errorContainerColor = Color.White,
                        disabledContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedLabelColor = MaterialTheme.colorScheme.onBackground
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    value = searchTextState,
                    onValueChange = { text ->
                        searchTextState = text
                    },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = stringResource(
                                R.string.icon_for_search_bar_description
                            )
                        )
                    },
                    label = {
                        Text(text = stringResource(R.string.search_label_text))
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            focusManager.clearFocus()
                        }
                    ),
                    trailingIcon = {
                        if (searchTextState.isNotBlank() && searchTextState.isNotEmpty())
                            IconButton(onClick = {
                                searchTextState = ""
                            }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_close),
                                    contentDescription = stringResource(
                                        R.string.close_icon_description
                                    )
                                )
                            }
                    }
                )
            }
            items(
                items = itemsUIState.value.filter {
                    it.name.contains(searchTextState, ignoreCase = true)
                },
                key = {
                    it.id
                }
            ) { item ->
                HomeItem(
                    name = item.name,
                    tags = item.tags,
                    amount = item.amount,
                    time = item.time,
                    onEditClick = {
                        usedIdState = item.id
                        showEditDialog = true
                    },
                    onDeleteClick = {
                        usedIdState = item.id
                        showDeleteDialog = true
                    }
                )
            }
        }
    }
}