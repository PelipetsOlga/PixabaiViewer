package com.example.home_ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController

@Composable
fun HomeComposable(
    navController: NavController,
    viewModel: HomeViewModel
) {
    val results by viewModel.results.collectAsState(listOf())


    Column {
        Text(text = "Home Fragment")
        Button(onClick = {
            navController.navigate(R.id.action_to_photo)
        }
        ) {
            Text("Go")
        }
        LazyColumn() {
            items(results.size, key = { index -> results[index].id }) { index ->
                Text(results[index].previewURL)
            }
        }
    }
}
