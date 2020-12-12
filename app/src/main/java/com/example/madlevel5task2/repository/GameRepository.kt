package com.example.madlevel5task2.repository

import android.content.Context
import com.example.madlevel5task2.database.GameRoomDatabase
import com.example.madlevel5task2.model.Game

class GameRepository(context: Context) {
    private var gameDao = GameRoomDatabase.getDatabase(context)!!.gameDao()

    suspend fun getAllGames(): List<Game> = gameDao.getAllGames()

    suspend fun insertGame(game: Game) = gameDao.insertGame(game)

    suspend fun deleteAllGames() = gameDao.deleteAllGames()
}