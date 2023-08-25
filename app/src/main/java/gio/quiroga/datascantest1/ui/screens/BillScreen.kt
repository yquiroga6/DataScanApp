package gio.quiroga.datascantest1.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import gio.quiroga.datascantest1.R
import gio.quiroga.datascantest1.model.BillViewModel
import gio.quiroga.datascantest1.ui.theme.DataScanTheme
import java.time.LocalDate

@Composable
fun BillScreen(billViewModel: BillViewModel = viewModel()) {
    val appState by billViewModel.uiState.collectAsState()

    Scaffold() { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = LocalDate.now().toString(),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = pluralStringResource(
                    R.plurals.you_has_purchased,
                    appState.products.size,
                    appState.products.size
                ),
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                style = MaterialTheme.typography.labelMedium,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BillScreenPreview() {
    DataScanTheme {
        BillScreen()
    }
}