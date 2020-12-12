package com.example.madlevel5task2.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.madlevel5task2.R
import com.example.madlevel5task2.databinding.FragmentGamesBinding
import com.example.madlevel5task2.model.Game
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class GamesFragment : Fragment() {
    private lateinit var fragmentGamesBinding: FragmentGamesBinding

    init {
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        fragmentGamesBinding = FragmentGamesBinding.inflate(inflater, container, false)

        fragmentGamesBinding.rvGames.let {
            it.adapter = GameAdapter(listOf(
                Game(
                    title = "Yakuza 5",
                    platform = "PS4",
                    releaseDate = GregorianCalendar(2020, 1, 11).time
                ),
                Game(
                    title = "Yakuza: Like a Dragon",
                    platform = "PS4, Windows, Steam, Xbox Series X/S, Xbox One (X)",
                    releaseDate = GregorianCalendar(2020, 10, 15).time
                )
            ))
            it.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

        return fragmentGamesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Handle toolbar
        val activity = requireActivity() as AppCompatActivity
        activity.findViewById<Toolbar>(R.id.toolbar).title = getString(R.string.fragment_games_toolbar_title)
        activity.supportActionBar!!.setDisplayHomeAsUpEnabled(false)

        // Handle fab
        activity.findViewById<FloatingActionButton>(R.id.fab).let {
            it.setImageDrawable(ContextCompat.getDrawable(requireContext(), android.R.drawable.ic_input_add))
            it.setOnClickListener {
                findNavController().navigate(R.id.action_gamesFragment_to_addGameFragment)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_games, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return true
    }
}