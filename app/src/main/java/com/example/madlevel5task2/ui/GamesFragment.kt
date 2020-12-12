package com.example.madlevel5task2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.madlevel5task2.R
import com.example.madlevel5task2.databinding.FragmentGamesBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class GamesFragment : Fragment() {
    private lateinit var fragmentGamesBinding: FragmentGamesBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        fragmentGamesBinding = FragmentGamesBinding.inflate(inflater, container, false)
        return fragmentGamesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Handle toolbar
        val activity = requireActivity() as AppCompatActivity
        activity.findViewById<Toolbar>(R.id.toolbar).title = getString(R.string.fragment_games_toolbar_title)
        activity.findViewById<FloatingActionButton>(R.id.fab).setImageDrawable(ContextCompat.getDrawable(requireContext(), android.R.drawable.ic_input_add))

        fragmentGamesBinding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_gamesFragment_to_addGameFragment)
        }
    }
}