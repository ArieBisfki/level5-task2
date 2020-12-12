package com.example.madlevel5task2.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.madlevel5task2.R
import com.example.madlevel5task2.databinding.FragmentAddGameBinding
import com.example.madlevel5task2.model.Game
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import java.util.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddGameFragment : Fragment() {

    private val gamesViewModel: GamesViewModel by viewModels()
    private lateinit var fragmentAddGameBinding: FragmentAddGameBinding

    init {
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentAddGameBinding = FragmentAddGameBinding.inflate(inflater)
        return fragmentAddGameBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Handle toolbar
        val activity = requireActivity() as AppCompatActivity
        activity.findViewById<Toolbar>(R.id.toolbar).title = getString(R.string.fragment_add_game_toolbar_title)
        activity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        // Handle fab
        activity.findViewById<FloatingActionButton>(R.id.fab).let { fab ->
            fab.setImageDrawable(ContextCompat.getDrawable(requireContext(), android.R.drawable.ic_menu_save))
            fab.setOnClickListener {

                val newGame = fragmentAddGameBinding.let { Game(
                    title = it.inputTitle.text.toString(),
                    platform = it.inputPlatform.text.toString(),
                    releaseDate = GregorianCalendar(
                        it.inputReleaseDateYear.text.toString().toInt(),
                        it.inputReleaseDateMonth.text.toString().toInt() - 1, // minus one because gregorian calendar uses indexes for months
                        it.inputReleaseDateDay.text.toString().toInt()
                    ).time
                )}

                gamesViewModel.insertGame(newGame)

                findNavController().popBackStack()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_add_game, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        findNavController().popBackStack()
        return true
    }
}