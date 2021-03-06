package com.hebreuyannis.cocktailapp.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.hebreuyannis.cocktailapp.CocktailApplication
import com.hebreuyannis.cocktailapp.R
import com.hebreuyannis.cocktailapp.SnackbarMessage
import com.hebreuyannis.cocktailapp.SnackbarMessageManager
import com.hebreuyannis.cocktailapp.mappers.toDomain
import com.hebreuyannis.cocktailapp.models.DrinkPresentation
import com.hebreuyannis.cocktailapp.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.layout_detail_drink.*
import kotlinx.android.synthetic.main.layout_favorite.toolbar
import kotlinx.android.synthetic.main.layout_info_drink.*
import kotlinx.android.synthetic.main.layout_ingredient.*
import javax.inject.Inject

class DrinkDetailFragment : MainNavigationFragment() {

    private val args: DrinkDetailFragmentArgs by navArgs()
    private var presentation: DrinkPresentation? = null
    private lateinit var ingredientRecyclerAdapter: IngredientRecyclerAdapter

    @Inject
    lateinit var viewModel: DetailViewModel

    @Inject
    lateinit var snackbarMessageManager: SnackbarMessageManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_detail_drink, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presentation = args.drinkPresentation
        presentation?.let { setupView(it) }
        viewModel.isFavorite(presentation!!)
        toolbar.run {
            setMenuItem(R.menu.drink_detail_menu)

            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.fav_button -> {
                        if (!viewModel.isFavorite) {
                            viewModel.addFavorites(presentation!!.toDomain())
                        } else {
                            viewModel.removeFavorite(presentation!!)
                        }
                        true
                    }
                    else -> false
                }
            }
        }

        setupRecyclerview()

        viewModel.command.observe(viewLifecycleOwner, {
            processCommand(it)
        })

        viewModel.favoriteCommand.observe(viewLifecycleOwner, {
            processFavoriteCommand(it)
        })

        setupSnackbar(
            snackbarMessage = viewModel.snackbarMessage,
            snackbarMessageManager = snackbarMessageManager,
            view = layout_detail,
            anchorView = layout_ingredient
        ) {}
    }

    private fun setupView(presentation: DrinkPresentation) {
        title_drink.text = presentation.name
        category_drink.text = getString(R.string.category, presentation.category)
        caption_detail.text = presentation.instruction

        Glide.with(requireContext())
            .load(presentation.drinkThumb)
            .into(image_detail)

        viewModel.searchInfo(presentation.name)
    }

    private fun setupRecyclerview() {
        ingredientRecyclerAdapter = IngredientRecyclerAdapter {}
        ingredient_recycler_view.adapter = ingredientRecyclerAdapter
    }

    private fun processCommand(command: DetailViewModel.Command) {
        when (command) {
            is DetailViewModel.Command.DisplayData -> {
                ingredient_progress_bar.visibility = View.GONE
                ingredient_recycler_view.visibility = View.VISIBLE
                ingredientRecyclerAdapter.submitList(command.data)
            }
            DetailViewModel.Command.Loading -> ingredient_progress_bar.visibility = View.VISIBLE
            is DetailViewModel.Command.Error -> viewModel.snackbarMessage.value =
                SnackbarMessage(R.string.generic_issue, R.string.retry, true, null)
        }
    }

    private fun processFavoriteCommand(favoriteCommand: DetailViewModel.FavoriteCommand) {
        when (favoriteCommand) {
            DetailViewModel.FavoriteCommand.IsFavorite -> {
                toolbar.menu.getItem(0).setIcon(R.drawable.ic_fav)
                (activity as MainActivity).invalidateOption()
            }
            DetailViewModel.FavoriteCommand.IsNotFavorite -> {
                toolbar.menu.getItem(0).setIcon(R.drawable.ic_no_fav)
                (activity as MainActivity).invalidateOption()
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as CocktailApplication).appComponent.inject(this)
    }
}