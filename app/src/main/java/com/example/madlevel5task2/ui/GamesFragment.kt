package com.example.madlevel5task2.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel5task2.R
import com.example.madlevel5task2.databinding.FragmentGamesBinding
import com.example.madlevel5task2.model.Game
import com.google.android.material.floatingactionbutton.FloatingActionButton

class GamesFragment : Fragment() {

    private lateinit var fragmentGamesBinding: FragmentGamesBinding

    private val gamesViewModel: GamesViewModel by viewModels()
    private val gamesList = mutableListOf<Game>()
    private val gameAdapter = GameAdapter(gamesList)

    init {
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        fragmentGamesBinding = FragmentGamesBinding.inflate(inflater, container, false)

        // Setup recyclerview
        fragmentGamesBinding.rvGames.let { rv ->
            rv.adapter = gameAdapter
            rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

            gamesViewModel.games.observe(viewLifecycleOwner, { reminders ->
                gamesList.clear()
                gamesList.addAll(reminders)
                gameAdapter.notifyDataSetChanged()
            })
            createItemTouchHelper().attachToRecyclerView(rv)
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
        activity.findViewById<FloatingActionButton>(R.id.fab).let { fab ->
            fab.setImageDrawable(ContextCompat.getDrawable(requireContext(), android.R.drawable.ic_input_add))
            fab.setOnClickListener {
                findNavController().navigate(R.id.action_gamesFragment_to_addGameFragment)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_games, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        gamesViewModel.deleteGames()
        return true
    }

    private fun createItemTouchHelper(): ItemTouchHelper {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ) = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val gameToDelete = gamesList[position]
                gamesViewModel.deleteGame(gameToDelete.id!!)
            }
        }
        return ItemTouchHelper(callback)
    }
}