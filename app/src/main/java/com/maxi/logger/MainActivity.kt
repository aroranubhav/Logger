package com.maxi.logger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.maxi.logger.ui.theme.LoggerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoggerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    App(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun App(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth(.8f),
            onClick = {
                //
            }
        ) {
            Text(text = "Verbose Log")
        }

        Button(
            modifier = Modifier
                .fillMaxWidth(.8f),
            onClick = {
                //
            }
        ) {
            Text(text = "Debug Log")
        }

        Button(
            modifier = Modifier
                .fillMaxWidth(.8f),
            onClick = {
                //
            }
        ) {
            Text(text = "Info Log")
        }

        Button(
            modifier = Modifier
                .fillMaxWidth(.8f),
            onClick = {
                //
            }
        ) {
            Text(text = "Warning Log")
        }

        Button(
            modifier = Modifier
                .fillMaxWidth(.8f),
            onClick = {
                //
            }
        ) {
            Text(text = "Error Log")
        }
    }
}

const val TAG = "MainActivityTAG"