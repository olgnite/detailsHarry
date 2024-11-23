package com.example.harry_details.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.harry_details.models.Character
import com.example.harry_details.navigation.CharacterDetailsScreen
import com.example.harry_details.network.HarryPoterInstance
import kotlinx.coroutines.launch

class DetailsViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    var characterState by mutableStateOf<Character?>(null)
    private val route = savedStateHandle.toRoute<CharacterDetailsScreen>()

    init {
        fetchCharacterById()
    }

    private fun fetchCharacterById() {
        viewModelScope.launch {
            try {
                val character = HarryPoterInstance.api.getCharacterById(route.id).firstOrNull()
                Log.d(TAG, character.toString())
                characterState = character
            } catch (e: Exception) {
                Log.e(TAG, "request error", e)
            }
        }
    }

    companion object {

        private const val TAG = "CharacterViewModel"
    }
}