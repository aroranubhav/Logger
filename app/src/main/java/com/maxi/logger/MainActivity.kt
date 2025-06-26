package com.maxi.logger

import android.os.Bundle
import android.util.Log
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
import androidx.compose.ui.tooling.preview.Preview
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
                Logger.v(TAG, "This is a VERBOSE log.")
            }
        ) {
            Text(text = "Verbose Log")
        }

        Button(
            modifier = Modifier
                .fillMaxWidth(.8f),
            onClick = {
                Logger.d(TAG, "This is a DEBUG log.")
            }
        ) {
            Text(text = "Debug Log")
        }

        Button(
            modifier = Modifier
                .fillMaxWidth(.8f),
            onClick = {
                Logger.i(TAG, "This is an INFO log.")
            }
        ) {
            Text(text = "Info Log")
        }

        Button(
            modifier = Modifier
                .fillMaxWidth(.8f),
            onClick = {
                Logger.w(TAG, "This is a WARNING log.")
            }
        ) {
            Text(text = "Warning Log")
        }

        Button(
            modifier = Modifier
                .fillMaxWidth(.8f),
            onClick = {
                Logger.e(TAG, "This is an ERROR log.")
            }
        ) {
            Text(text = "Error Log")
        }
    }
}

const val TAG = "MainActivityTAG"