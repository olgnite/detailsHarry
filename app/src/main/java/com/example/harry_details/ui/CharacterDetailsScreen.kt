package com.example.harry_details.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.example.harry_details.models.Character
import com.example.harry_details.viewmodel.DetailsViewModel


@Composable
fun CharacterDetailsScreen(
    viewModel: DetailsViewModel = hiltViewModel(),
) {
    CharacterItem(viewModel.characterState)
}

@Composable
fun CharacterItem(character: Character?) {
    Scaffold() {
        if (character != null) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(it),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .height(240.dp)
                        .align(Alignment.CenterHorizontally),
                    model = character.image,
                    contentDescription = null,
                )
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
            }
        }
    }
}