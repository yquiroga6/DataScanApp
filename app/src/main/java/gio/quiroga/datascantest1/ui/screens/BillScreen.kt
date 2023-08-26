package gio.quiroga.datascantest1.ui.screens

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import gio.quiroga.datascantest1.R
import gio.quiroga.datascantest1.model.BillViewModel
import gio.quiroga.datascantest1.ui.components.rememberReceipt
import gio.quiroga.datascantest1.ui.theme.DataScanTheme
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BillScreen(billViewModel: BillViewModel = viewModel(), onBackToProducts: () -> Unit) {
    //val appState by billViewModel.uiState.collectAsState()

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
        }
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
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = stringResource(R.string.greeting, billViewModel.getClientName() ?: ""),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 32.dp)
                    .fillMaxWidth(),
                style = MaterialTheme.typography.titleMedium,
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
                style = MaterialTheme.typography.labelMedium,
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = stringResource(R.string.purchase),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp),
                style = MaterialTheme.typography.titleLarge
            )
            LazyColumn() {
                items(4) {
                    ItemRow()
                }
            }
        }
    }
}

@Composable
fun ItemRow() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "2", style = MaterialTheme.typography.titleSmall)
        Text(text = "Product1", style = MaterialTheme.typography.titleSmall)
        Text(text = "45000", style = MaterialTheme.typography.titleSmall)
    }
}

@Preview(showBackground = true)
@Composable
fun BillScreenPreview() {
    DataScanTheme {
        BillScreen(onBackToProducts = {})
    }
}