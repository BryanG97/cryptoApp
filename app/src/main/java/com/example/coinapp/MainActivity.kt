package com.example.coinapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.coinapp.models.Asset
import com.example.coinapp.ui.theme.CoinAppTheme
import com.example.coinapp.views.AssetRow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            CoinAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()){ innerPadding ->
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        AssetRow(
                            Asset(id = "1",
                                name = "Bitcoin",
                                symbol = "BTC",
                                percentage = 5.38,
                                price = "87908"
                            )
                        )
                        Spacer(modifier = Modifier.size(16.dp))
                        AssetRow(
                            Asset(id = "2",
                                name = "Ethereum",
                                symbol = "ETH",
                                percentage = -8.28,
                                price = "1800"
                            )
                        )
                    }
                }
            }
        }
    }
}

/*
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello changess $name!",
        modifier = modifier
    )
    AssetRowPreview()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CoinAppTheme {
        Greeting("Android")
    }
}*/
