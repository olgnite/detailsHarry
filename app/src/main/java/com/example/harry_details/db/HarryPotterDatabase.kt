package com.example.harry_details.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.harry_details.models.Character

@Database(
    version = 1,
    entities = [Character::class],
)
abstract class HarryPotterDatabase : RoomDatabase() {

    abstract fun getCharacterDao(): CharacterDao

    companion object {

        fun create(context: Context): HarryPotterDatabase {
            return Room.databaseBuilder(context, HarryPotterDatabase::class.java, "HarryPotter")
                .build()
        }
    }
}
