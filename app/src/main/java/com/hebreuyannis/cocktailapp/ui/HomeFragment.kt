package com.hebreuyannis.cocktailapp.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.hebreuyannis.cocktailapp.CocktailApplication
import com.hebreuyannis.cocktailapp.R
import com.hebreuyannis.cocktailapp.SnackbarMessage
import com.hebreuyannis.cocktailapp.SnackbarMessageManager
import com.hebreuyannis.cocktailapp.viewmodel.MainViewModel
import com.hebreuyannis.data_remote.exception.LoadingException
import com.hebreuyannis.data_remote.exception.NetworkException
import kotlinx.android.synthetic.main.layout_favorite.toolbar
import kotlinx.android.synthetic.main.layout_home.*
import javax.inject.Inject

class HomeFragment : MainNavigationFragment() {

    private lateinit var recyclerAdapter: DrinkHomeRecyclerAdapter

    @Inject
    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var snackbarMessageManager: SnackbarMessageManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_home, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel.randomLoad()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        toolbar.run {
            setMenuItem(R.menu.home_search_menu)

            (menu.findItem(R.id.search).actionView as SearchView).apply {
                setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        newText?.let { mainViewModel.searchByName(it) }
                        return true
                    }
                })
            }

            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.search -> {
                        true
                    }
                    else -> false
                }
            }
        }
        mainViewModel.command.observe(viewLifecycleOwner, { processCommand(it) })
        mainViewModel.currentPresentation.observe(viewLifecycleOwner, {
            drinks_recycler_view.visibility = View.VISIBLE
            drink_progress_bar.visibility = View.GONE
            recyclerAdapter.submitList(it)
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as CocktailApplication).appComponent.inject(this)
    }

    private fun setupView() {
        setupSnackbar(
            mainViewModel.snackbarMessage,
            snackbarMessageManager,
            home_layout,
            anchorView = drinks_recycler_view
        ) {
            when (it) {
                R.string.retry -> {
                    mainViewModel.randomLoad()
                }
                else -> Unit
            }
        }
        recyclerAdapter = DrinkHomeRecyclerAdapter(itemClickListener = {
            (activity as MainActivity).forceVisibleBottomBar()
            findNavController().navigate(
                HomeFragmentDirections.actionNavigationHomeToNavigationDrinkDetail(
                    it
                )
            )
        })
        drinks_recycler_view.adapter = recyclerAdapter
        var isHide = false
        var scrollUp = false
        drinks_recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                scrollUp = dy > 0
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                when (newState) {
                    AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL -> {
                        if (!isHide) {
                            isHide = true
                            (activity as MainActivity).hideBottomBar()
                        }
                    }
                    AbsListView.OnScrollListener.SCROLL_STATE_IDLE -> {
                        if (!scrollUp) {
                            isHide = false
                            (activity as MainActivity).revealBottomBar()
                        }
                    }
                }
            }
        })
    }

    private fun processCommand(command: MainViewModel.Command) {
        when (command) {
            MainViewModel.Command.Loading -> {
                drink_progress_bar.visibility = View.VISIBLE
                drinks_recycler_view.visibility = View.INVISIBLE
            }
            is MainViewModel.Command.Error -> {
                when (command.exception) {
                    is LoadingException -> mainViewModel.snackbarMessage.value =
                        SnackbarMessage(R.string.loading_issue, R.string.retry, true, null)
                    is NetworkException -> mainViewModel.snackbarMessage.value =
                        SnackbarMessage(R.string.network_issue, R.string.retry, true, null)
                }
            }
        }
    }
}