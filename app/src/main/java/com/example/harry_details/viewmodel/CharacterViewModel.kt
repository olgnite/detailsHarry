package com.example.harry_details.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harry_details.data.CharacterRepository
import com.example.harry_details.models.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val repository: CharacterRepository,
) : ViewModel() {

    val state = repository.characters
        .map(State::Content)
        .stateIn(viewModelScope, SharingStarted.Lazily, State.Loading)

    init {
        fetchCharacters()
    }

    private fun fetchCharacters() {
        repository.requestCharacters()
    }

    sealed interface State {

        data object Loading : State

        data class Content(val characters: List<Character>) : State
    }

    companion object {

        private const val TAG = "CharacterViewModel"
    }
}