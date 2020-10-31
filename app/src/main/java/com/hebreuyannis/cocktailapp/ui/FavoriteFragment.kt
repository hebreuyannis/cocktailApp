package com.hebreuyannis.cocktailapp.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.hebreuyannis.cocktailapp.CocktailApplication
import com.hebreuyannis.cocktailapp.R
import com.hebreuyannis.cocktailapp.helper.SwipeToDeleteCallback
import com.hebreuyannis.cocktailapp.viewmodel.FavoriteViewModel
import kotlinx.android.synthetic.main.layout_favorite.*
import javax.inject.Inject

class FavoriteFragment : MainNavigationFragment() {

    @Inject
    lateinit var favoriteViewModel: FavoriteViewModel

    lateinit var favoriteAdapter: FavoriteRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoriteViewModel.loadData()
        setupRecycler()
        favoriteViewModel.command.observe(viewLifecycleOwner, { processCommand(it) })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as CocktailApplication).appComponent.inject(this)
    }

    private fun setupRecycler() {
        favoriteAdapter = FavoriteRecyclerAdapter {

        }
        favorite_recycler_view.adapter = favoriteAdapter
        val swipeHandler = object : SwipeToDeleteCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = favoriteAdapter.getItemWith(viewHolder.adapterPosition)
                favoriteViewModel.deleteItem(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(favorite_recycler_view)
    }

    private fun processCommand(command: FavoriteViewModel.Command) {
        when (command) {
            FavoriteViewModel.Command.Loading -> {
                planet_progress_bar.visibility = View.VISIBLE
            }
            is FavoriteViewModel.Command.Data -> {
                planet_progress_bar.visibility = View.INVISIBLE
                favoriteAdapter.submitList(command.drinskPresentation)
            }
        }
    }
}