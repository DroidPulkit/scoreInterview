package com.thescore.interview.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.thescore.interview.databinding.ActivityMainBinding
import com.thescore.interview.repositories.teamService

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private lateinit var adapter: TeamsAdapter

    private val viewModel by viewModels<MainViewModel>(factoryProducer = {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {

                return when(modelClass) {
                    MainViewModel::class.java -> MainViewModel(teamService) as T
                    else -> throw IllegalArgumentException("Unsupported ViewModel type")
                }
            }
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        adapter = TeamsAdapter()
        binding?.recyclerview?.adapter = adapter

        viewModel.fetchData.observe(this) {
            if (it.isSuccess)
            {
                it.getOrNull()?.let { data ->
                    adapter.updateData(data)
                    adapter.notifyDataSetChanged()
                }
            } else {
                //Make error screen in future
            }
        }

    }
}