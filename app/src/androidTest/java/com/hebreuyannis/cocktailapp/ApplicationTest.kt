package com.hebreuyannis.cocktailapp

import androidx.test.core.app.ActivityScenario
import com.hebreuyannis.cocktailapp.ui.MainActivity
import org.junit.Test

class ApplicationTest {
    @Test
    fun runApp() {
        ActivityScenario.launch(MainActivity::class.java)
    }
}