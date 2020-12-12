package com.example.madlevel5task2.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "game")
data class Game(
    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "platform")
    val platform: String,

    @ColumnInfo(name = "releaseDate")
    val releaseDate: Date,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long? = null
) : Comparable<Game> {
    override fun compareTo(other: Game) =
        this.releaseDate.compareTo(other.releaseDate)

    @delegate:Ignore
    val releaseDateString: String by lazy {
        SimpleDateFormat("d MMMM y", Locale.ENGLISH).format(releaseDate)
    }
}