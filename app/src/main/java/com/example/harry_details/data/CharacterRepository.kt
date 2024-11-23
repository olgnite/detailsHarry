package com.example.harry_details.data

import com.example.harry_details.db.CharacterDao
import com.example.harry_details.models.Character
import com.example.harry_details.network.HarryPotterApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

interface CharacterRepository {

    val characters: Flow<List<Character>>

    fun requestCharacters()
}

class CharacterRepositoryImpl @Inject constructor(
    private val corouineScope: CoroutineScope,
    private val api: HarryPotterApiService,
    private val dao: CharacterDao,
) : CharacterRepository {

    override val characters: Flow<List<Character>> = dao.allCharacters

    override fun requestCharacters() {
        corouineScope.launch {
            val characters = runCatching { api.getCharacters() }
            characters.fold(
                onSuccess = { characters -> dao.insertCharacters(characters) },
                onFailure = { th ->  },
            )
        }
    }
}