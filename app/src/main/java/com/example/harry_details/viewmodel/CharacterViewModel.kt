package com.example.harry_details.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harry_details.network.HarryPoterInstance
import kotlinx.coroutines.launch

class CharacterViewModel : ViewModel() {
    val characters = mutableStateListOf<Character>()

    init {
        fetchCharacters()
    }

    private fun fetchCharacters() {
        viewModelScope.launch {
            try {
                val charactersList = HarryPoterInstance.api.getCharacters()
                characters.clear()
                characters.addAll(charactersList)
            } catch (e: Exception) {
                Log.e(TAG, "request error", e)
            }
        }
    }

    companion object {

        private const val TAG = "CharacterViewModel"
    }
}