package com.hebreuyannis.cocktailapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.hebreuyannis.cocktailapp.R
import kotlinx.android.synthetic.main.layout_favorite.*

class DrinkDetailFragment : MainNavigationFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_detail_drink, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.run {
            setMenuItem(R.menu.drink_detail_menu)

            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.fav_button -> {
                        findNavController().navigate(R.id.navigation_drink_detail)
                        true
                    }
                    else -> false
                }
            }
        }
    }
}