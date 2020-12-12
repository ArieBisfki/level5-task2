package com.example.madlevel5task2.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel5task2.R
import com.example.madlevel5task2.databinding.ItemGameBinding
import com.example.madlevel5task2.model.Game

class GameAdapter(
    private val gamesList: List<Game>
) : RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemGameBinding = ItemGameBinding.bind(itemView)

        fun databind(game: Game) {
            itemGameBinding.let {
                it.tvTitle.text = game.title
                it.tvPlatform.text = game.platform
                it.tvReleaseDate.text = itemView.context.getString(R.string.fragment_games_release_date, game.releaseDateString)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.item_game, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(gamesList[position])
    }

    override fun getItemCount(): Int = gamesList.size
}