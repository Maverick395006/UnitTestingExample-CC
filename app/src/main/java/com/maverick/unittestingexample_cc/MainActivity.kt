package com.maverick.unittestingexample_cc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.NetworkOnMainThreadException
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.maverick.unittestingexample_cc.adapter.ProductAdapter
import com.maverick.unittestingexample_cc.databinding.ActivityMainBinding
import com.maverick.unittestingexample_cc.utils.NetworkResult
import com.maverick.unittestingexample_cc.viewmodels.MainViewModel
import com.maverick.unittestingexample_cc.viewmodels.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel
    lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvProductList.layoutManager = GridLayoutManager(this, 2)

        val repository = (application as StoreApplication).productRepository

        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]

        mainViewModel.getProducts()

        mainViewModel.products.observe(this) {
            when (it) {
                is NetworkResult.Success -> {
                    Log.d("CHEEZ", it.data.toString())
                    productAdapter = ProductAdapter(it.data!!)
                    binding.rvProductList.adapter = productAdapter
                }
                is NetworkResult.Error -> {

                }
                is NetworkResult.Loading -> TODO()
            }
        }

    }

}