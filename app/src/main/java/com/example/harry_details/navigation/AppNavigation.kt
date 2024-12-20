package com.example.details.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.harry_details.ui.CharacterDetailsScreen
import com.example.harry_details.ui.CharacterListScreen
import kotlinx.serialization.Serializable

@Serializable
data object CharacterListScreen

@Serializable
data class CharacterDetailsScreen1(val id: String)

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = CharacterListScreen) {
        composable<CharacterListScreen> {
            CharacterListScreen(
                onCharacterClick = { navController.navigate(CharacterDetailsScreen1(it.id)) }
            )
        }
        composable<CharacterDetailsScreen1> {
            CharacterDetailsScreen(
                viewModel = viewModel()
            )
        }
    }
}