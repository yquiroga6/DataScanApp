package gio.quiroga.datascantest1.ui.screens

import android.icu.text.NumberFormat
import android.icu.util.Currency
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import gio.quiroga.datascantest1.R
import gio.quiroga.datascantest1.model.AppState
import gio.quiroga.datascantest1.model.ProductsState
import gio.quiroga.datascantest1.model.ProductsViewModel
import gio.quiroga.datascantest1.services.data_models.Producto
import gio.quiroga.datascantest1.ui.components.rememberReceipt
import gio.quiroga.datascantest1.ui.theme.DataScanTheme
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsScreen(productsViewModel: ProductsViewModel = viewModel(), onSeeBill: () -> Unit) {
    val sheetState = rememberModalBottomSheetState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.title_activity_products_screen))
                },
                actions = {
                    if (productsViewModel.showActionButton)
                        ActionButton {
                            productsViewModel.initData()
                            onSeeBill()
                        }
                }

            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(text = stringResource(R.string.add_product)) },
                icon = { Icon(Icons.Filled.Add, contentDescription = "") },
                onClick = {
                    productsViewModel.isShowingBottomSheet(true)
                }
            )
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { contentPadding ->
        if (productsViewModel.showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    productsViewModel.isShowingBottomSheet(false)
                },
                sheetState = sheetState,
                modifier = Modifier.fillMaxHeight()
            ) {
                // Sheet content
                ProductSheetDialog(
                    value = productsViewModel.textFieldValue,
                    onValueChange = {
                        productsViewModel.changeTextFieldValue(it)
                    },
                    onSubmit = {
                        productsViewModel.getProductById()
                    },
                    isError = productsViewModel.error,
                    isLoading = productsViewModel.isLoading
                )
            }
        }
        //Box(modifier = Modifier.padding(contentPadding)) { /* ... */ }
        LazyColumn(modifier = Modifier.padding(contentPadding)) {
            val productos = productsViewModel.getProductsFromState()
            items(productos.size) { index ->
                ProductCard(productos[index])
            }
        }
    }
}

@Composable
fun ActionButton(action: () -> Unit) {
    IconButton(onClick = {
        action()
    }) {
        Icon(
            imageVector = rememberReceipt(),
            contentDescription = "Bills",
            Modifier.height(32.dp)
        )
    }
}

@Composable
fun ProductCard(product: Producto) {
    var expanded by remember { mutableStateOf(false) }
    val format: NumberFormat = NumberFormat.getCurrencyInstance()
    format.currency = Currency.getInstance("COP")

    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp, pressedElevation = 8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { expanded = !expanded }

    ) {
        Column(
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = getImageUrl(product.id),
                    contentDescription = "Product image",
                    modifier = Modifier
                        .padding(16.dp)
                        .height(64.dp)
                        .width(64.dp)
                )
                Column {
                    Text(
                        text = product.nombre ?: "",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )

                    Text(
                        text = format.format(product.valor) ?: "",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
            }
            if (expanded) {
                Text(
                    text = product.id ?: "",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun ProductSheetDialog(
    value: String, onValueChange: (String) -> Unit,
    onSubmit: (KeyboardActionScope) -> Unit,
    isError: Boolean,
    isLoading: Boolean
) {
    val focusRequester = remember { FocusRequester() }

    Box {
        Column(
            modifier = Modifier.padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.input_product_id),
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .align(alignment = Alignment.Start)
            )
            TextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .padding(bottom = 32.dp)
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Send,
                    capitalization = KeyboardCapitalization.Characters
                ),
                isError = isError,
                supportingText = {
                    if (isError) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(R.string.product_no_found),
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
                keyboardActions = KeyboardActions(onSend = onSubmit),
                label = { Text(stringResource(R.string.product_id_te_label)) },
                singleLine = true
            )
            LaunchedEffect("") {
                delay(300)
                focusRequester.requestFocus()
            }
        }
        if (isLoading)
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
    }
}

fun getImageUrl(id: String): String {
    when (id) {
        "P000000001" -> return "https://cdn-icons-png.flaticon.com/512/2580/2580779.png"
        "P000000002" -> return "https://cdn-icons-png.flaticon.com/128/7348/7348926.png"
        "P000000003" -> return "https://cdn-icons-png.flaticon.com/256/7439/7439205.png"
        "P000000004" -> return "https://cdn-icons-png.flaticon.com/256/5256/5256692.png"
    }
    return ""
}

@Preview(showBackground = true)
@Composable
fun ProductsScreenPreview() {
    DataScanTheme {
        ProductsScreen(onSeeBill = { })
    }
}