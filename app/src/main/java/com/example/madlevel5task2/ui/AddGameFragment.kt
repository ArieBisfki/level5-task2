package com.example.madlevel5task2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.madlevel5task2.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddGameFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Handle toolbar
        val activity = requireActivity() as AppCompatActivity
        activity.findViewById<Toolbar>(R.id.toolbar).title = getString(R.string.fragment_add_game_toolbar_title)
        activity.findViewById<FloatingActionButton>(R.id.fab)
            .setImageDrawable(ContextCompat.getDrawable(requireContext(), android.R.drawable.ic_menu_save))

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().popBackStack()
        }
    }
}