package com.example.madlevel5task2.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.madlevel5task2.database.GameRoomDatabase
import com.example.madlevel5task2.model.Game

class GamesRepository(context: Context) {
    private var gameDao = GameRoomDatabase.getDatabase(context)!!.gameDao()

    private var allGamesMediatorLiveData: MediatorLiveData<List<Game>>? = null

    fun getAllGames(): LiveData<List<Game>> {
        if (allGamesMediatorLiveData == null) {
            val daoGamesList = gameDao.getAllGames()
            val allGamesList = MediatorLiveData<List<Game>>()
            allGamesList.addSource(daoGamesList) { result ->
                println(result.sorted())
                allGamesList.value = result.sorted()
            }
            allGamesMediatorLiveData = allGamesList
        }

        return allGamesMediatorLiveData as LiveData<List<Game>>
    }

    suspend fun insertGame(game: Game) = gameDao.insertGame(game)

    suspend fun deleteAllGames() = gameDao.deleteAllGames()

    suspend fun deleteGame(id: Long) = gameDao.deleteGame(id)
}