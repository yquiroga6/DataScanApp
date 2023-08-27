package gio.quiroga.datascantest1.ui.screens

import android.icu.text.NumberFormat
import android.icu.util.Currency
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import gio.quiroga.datascantest1.R
import gio.quiroga.datascantest1.model.BillViewModel
import gio.quiroga.datascantest1.services.data_models.Producto
import gio.quiroga.datascantest1.ui.components.DashedDivider
import gio.quiroga.datascantest1.ui.theme.DataScanTheme
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BillScreen(billViewModel: BillViewModel = viewModel(), onBackToProducts: () -> Unit) {
    val format: NumberFormat = NumberFormat.getCurrencyInstance()
    format.currency = Currency.getInstance("COP")

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.receipt))
                },
                navigationIcon = {
                    IconButton(onClick = { onBackToProducts() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "back arrow")
                    }
                }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(text = stringResource(R.string.to_purchase)) },
                icon = { Icon(Icons.Filled.ShoppingCart, contentDescription = "") },
                onClick = {

                }
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = LocalDate.now().toString(),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp),
                style = TextStyle(color = Color.DarkGray)
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = stringResource(R.string.greeting, billViewModel.getClientName() ?: ""),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 32.dp)
                    .fillMaxWidth(),
                style = MaterialTheme.typography.titleMedium.copy(color = lightColorScheme().primary),
            )
            Text(
                text = pluralStringResource(
                    R.plurals.you_has_purchased,
                    billViewModel.getProductsSizefromState(),
                    billViewModel.getProductsSizefromState()
                ),
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .fillMaxWidth(),
                style = MaterialTheme.typography.labelMedium.copy(color = Color.Gray),
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = stringResource(R.string.purchase),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp),
                style = MaterialTheme.typography.titleLarge
            )
            LazyColumn(modifier = Modifier.padding(horizontal = 32.dp)) {
                val groups= billViewModel.getGroupsById().values
                items(groups.size) { i ->
                    ItemRow(
                        format = format,
                        quantity = groups.elementAt(i).size,
                        name = groups.elementAt(i).first().nombre ?: "",
                        sum = groups.elementAt(i).sumOf { it.valor ?: 0 }
                    )
                    DashedDivider(
                        color = Color.LightGray,
                        thickness = 1.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
            Divider(
                color = lightColorScheme().primary,
                thickness = 3.dp,
                modifier = Modifier.padding(horizontal = 32.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp),
            ) {
                Text(
                    text = stringResource(R.string.subtotal),
                    modifier = Modifier
                        .padding(horizontal = 32.dp)
                        .weight(1F),
                    style = MaterialTheme.typography.titleSmall.copy(color = Color.Gray),
                )
                Text(
                    text = format.format(billViewModel.sumSubtotal()),
                    style = MaterialTheme.typography.titleSmall.copy(color = Color.Gray),
                    modifier = Modifier.padding(horizontal = 32.dp)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp),
            ) {
                Text(
                    text = stringResource(R.string.discounts),
                    modifier = Modifier
                        .padding(horizontal = 32.dp)
                        .weight(1F),
                    style = MaterialTheme.typography.titleSmall.copy(color = Color.Gray),
                )
                Text(
                    text = "(${format.format(45000)})",
                    style = MaterialTheme.typography.titleSmall.copy(color = Color.Gray),
                    modifier = Modifier.padding(horizontal = 32.dp)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
            ) {
                Text(
                    text = stringResource(R.string.total),
                    modifier = Modifier
                        .padding(horizontal = 32.dp)
                        .weight(1F),
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    text = format.format(45000),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(horizontal = 32.dp)
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            DashedDivider(
                color = MaterialTheme.colorScheme.secondary,
                thickness = 1.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                intervals = floatArrayOf(20f, 10f),
            )
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun ItemRow(format: NumberFormat, quantity: Int, name: String, sum: Int) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
    ) {
        Text(
            text = quantity.toString(),
            style = MaterialTheme.typography.titleMedium.copy(color = Color.Gray),
            modifier = Modifier.padding(end = 16.dp)
        )
        Text(
            text = name,
            style = MaterialTheme.typography.titleMedium.copy(color = Color.Gray),
            modifier = Modifier.weight(1F)
        )
        Text(text = format.format(sum), style = MaterialTheme.typography.titleMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun BillScreenPreview() {
    DataScanTheme {
        BillScreen(onBackToProducts = {})
    }
}