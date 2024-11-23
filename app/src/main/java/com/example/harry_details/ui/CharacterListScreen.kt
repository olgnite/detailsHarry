package com.example.harry_details.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.example.harry_details.models.Character
import com.example.harry_details.viewmodel.CharacterViewModel

@Composable
fun CharacterListScreen(
    onCharacterClick: (Character) -> Unit,
    viewModel: CharacterViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    when (val state = state) {
        CharacterViewModel.State.Loading -> {
            Box(
                contentAlignment = Alignment.Center,
            ) {
                Text("вау это загрузка")
            }
        }
        is CharacterViewModel.State.Content -> {
            Content(
                characters = state.characters,
                onCharacterClick = onCharacterClick,
            )
        }
    }
}

@Composable
fun Content(
    characters: List<Character>,
    onCharacterClick: (Character) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(characters, key = { it.id }) {
            CharacterItem(
                character = it,
                onClick = { onCharacterClick(it) }
            )
        }
    }
}

@Composable
fun CharacterItem(
    character: Character,
    onClick: () -> Unit,
) {
    OutlinedCard(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                modifier = Modifier.height(120.dp),
                model = character.image,
                contentDescription = null,
            )
            Spacer(Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Name:",
                    fontSize = 18.sp
                )
                Text(
                    text = character.name,
                    fontSize = 18.sp
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "House:"
                )
                Text(
                    text = character.house
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Actor:"
                )
                Text(
                    text = character.actor
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}