package com.example.artshop.ui.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.artshop.appstate.BasketEvent
import com.example.artshop.R
import com.example.artshop.ui.viewmodels.BasketViewModel
import com.example.artshop.ui.viewmodels.BasketViewModelFactory
import com.example.artshop.di.AppContainer

@Composable
fun CheckoutScreen (navController: NavHostController, appContainer: AppContainer) {
    val basketViewModel: BasketViewModel = viewModel(factory = BasketViewModelFactory(appContainer))
    val basketUiState by basketViewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopBar(navController, stringResource(R.string.Checkout))
        }) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            item {
                Text(
                    text = stringResource(R.string.PaymentOptions),
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 16.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.titleLarge,
                )

                Divider(
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
                )

                PaymentOptions()

                Divider(
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
                )

                Text(
                    text = "${stringResource(R.string.PhotoPrice)} ${basketUiState.totalPrice}",
                    modifier = Modifier
                        .padding(bottom = 16.dp, top = 30.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.titleLarge,
                )

                val context = LocalContext.current
                val noPayment = stringResource(R.string.NoPayment)
                Column(modifier = Modifier
                    .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally){


                    Button(
                        onClick = {
                            Toast.makeText(context, noPayment, Toast.LENGTH_LONG).show()
                            basketViewModel.onEvent(BasketEvent.ClearBasket)
                            navController.navigate("Home")
                        },
                        modifier = Modifier
                            .width(150.dp)
                            .height(50.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.Pay),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PaymentOptions() {
    var selectedOption by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        PaymentOptionRow(
            text = stringResource(R.string.CreditCard),
            selected = selectedOption == "Credit Card",
            onOptionSelected = { selectedOption = "Credit Card" }
        )
        PaymentOptionRow(
            text = stringResource(R.string.Paypal),
            selected = selectedOption == "PayPal",
            onOptionSelected = { selectedOption = "PayPal" }
        )
        PaymentOptionRow(
            text = stringResource(R.string.BankTransfer),
            selected = selectedOption == "Bank Transfer",
            onOptionSelected = { selectedOption = "Bank Transfer" }
        )
    }
}

@Composable
fun PaymentOptionRow(text: String, selected: Boolean, onOptionSelected: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = onOptionSelected
        )
        Text(
            text = text,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}
