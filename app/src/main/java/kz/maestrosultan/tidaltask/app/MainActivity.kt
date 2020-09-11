package kz.maestrosultan.tidaltask.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import kz.maestrosultan.tidaltask.R
import kz.maestrosultan.tidaltask.core.extensions.dataBinding
import kz.maestrosultan.tidaltask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by dataBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.navHostContainer).navigateUp()
    }
}