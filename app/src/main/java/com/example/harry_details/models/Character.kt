package com.example.harry_details.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("characters")
data class Character(
    @PrimaryKey
    val id: String,
    val name: String,
    val house: String,
    val actor: String,
    val image: String
)
