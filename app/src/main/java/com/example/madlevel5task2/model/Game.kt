package com.example.madlevel5task2.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "game")
data class Game(
    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "platform")
    val platform: String,

    @ColumnInfo(name = "date")
    val date: Date,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long?
) : Comparable<Game> {
    override fun compareTo(other: Game) =
        this.date.compareTo(other.date)
}