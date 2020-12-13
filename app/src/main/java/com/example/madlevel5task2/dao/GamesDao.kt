package com.example.madlevel5task2.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.madlevel5task2.model.Game

@Dao
interface GamesDao {
    @Query("SELECT * FROM game")
    fun getAllGames(): LiveData<List<Game>>

    @Insert
    suspend fun insertGame(game: Game)

    @Query("DELETE FROM game")
    suspend fun deleteAllGames()

    @Query("DELETE FROM game WHERE id == :id")
    suspend fun deleteGame(id: Long)
}