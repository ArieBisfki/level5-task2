package com.example.madlevel5task2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.madlevel5task2.dao.GamesDao
import com.example.madlevel5task2.model.Game

@Database(entities = [Game::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class GameRoomDatabase() : RoomDatabase() {
    abstract fun gameDao(): GamesDao

    companion object {
        private const val DATABASE_NAME = "GAME_DATABASE"

        @Volatile
        private var instance: GameRoomDatabase? = null

        fun getDatabase(context: Context): GameRoomDatabase? {
            if (instance == null) {
                synchronized(GameRoomDatabase::class.java) {
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            context.applicationContext,
                            GameRoomDatabase::class.java, DATABASE_NAME
                        ).build()
                    }
                }
            }
            return instance
        }
    }
}