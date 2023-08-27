package gio.quiroga.datascantest1.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import gio.quiroga.datascantest1.R
import gio.quiroga.datascantest1.model.ClientIdViewModel
import gio.quiroga.datascantest1.ui.theme.DataScanTheme
import kotlinx.coroutines.delay

@Composable
fun ClientIDScreen(
    clientIdViewModel: ClientIdViewModel = viewModel(),
    onSeeAllProducts: () -> Unit
) {
    if (clientIdViewModel.isVerified) {
        clientIdViewModel.changeVerified(false)
        onSeeAllProducts()
    }

    Card(
        modifier = Modifier
            .padding(40.dp)
            .aspectRatio(1.55F),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
    ) {
        Box {
            Column(
                modifier = Modifier.padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.input_id),
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .align(alignment = Alignment.Start)
                )
                EditNumberField(
                    value = clientIdViewModel.textFieldValue,
                    onValueChange = { clientIdViewModel.changeTextFieldValue(it) },
                    onSubmit = { clientIdViewModel.verifyClientId() },
                    isError = clientIdViewModel.error,
                    modifier = Modifier
                        .padding(bottom = 32.dp)
                        .fillMaxWidth()
                )
                //Spacer(modifier = Modifier.height(150.dp))
            }
            if (clientIdViewModel.isLoading)
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
        }
    }
    LaunchedEffect("") {
        clientIdViewModel.initData()
    }
}

@Composable
fun EditNumberField(
    value: String,
    onValueChange: (String) -> Unit,
    onSubmit: (KeyboardActionScope) -> Unit,
    isError: Boolean,
    modifier: Modifier = Modifier
) {
    val focusRequester = remember { FocusRequester() }

    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.focusRequester(focusRequester),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword,
            imeAction = ImeAction.Send
        ),
        isError = isError,
        supportingText = {
            if (isError) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.client_no_found),
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
        keyboardActions = KeyboardActions(onSend = onSubmit),
        label = { Text(stringResource(R.string.id_card)) },
        singleLine = true
    )
    LaunchedEffect("") {
        delay(300)
        focusRequester.requestFocus()
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DataScanTheme {
        ClientIDScreen(onSeeAllProducts = { })
    }
}