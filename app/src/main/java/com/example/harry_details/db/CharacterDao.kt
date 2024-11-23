package com.example.harry_details.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.harry_details.models.Character
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @get:Query("SELECT * FROM characters")
    val allCharacters: Flow<List<Character>>

    @Query("SELECT * FROM characters WHERE id = :id")
    fun getCharacter(id: String): Flow<Character>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacter(character: Character)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacters(characters: List<Character>)
}
