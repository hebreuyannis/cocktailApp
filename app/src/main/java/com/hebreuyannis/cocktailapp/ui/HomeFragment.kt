package com.hebreuyannis.cocktailapp.ui

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.findNavController
import com.hebreuyannis.cocktailapp.R
import kotlinx.android.synthetic.main.layout_favorite.*

class HomeFragment : MainNavigationFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_home, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.run {
            setMenuItem(R.menu.home_search_menu)

            (menu.findItem(R.id.search).actionView as SearchView).apply {
                setOnQueryTextListener(object :SearchView.OnQueryTextListener{
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
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
    }
}