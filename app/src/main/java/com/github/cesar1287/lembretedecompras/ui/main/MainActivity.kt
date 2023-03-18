package com.github.cesar1287.lembretedecompras.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.cesar1287.lembretedecompras.databinding.ActivityMainBinding
import com.github.cesar1287.lembretedecompras.model.Product
import com.github.cesar1287.lembretedecompras.ui.newproduct.NewProductActivity

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    private val mainListAdapter: MainListAdapter by lazy {
        MainListAdapter {
            mainViewModel.delete(it)
        }
    }

    private val newProductRequest =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                it.data?.getStringExtra(NewProductActivity.EXTRA_REPLY)?.let { productName ->
                    val product = Product(productName)
                    mainViewModel.insert(product)
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setUpRecyclerView()
        initObserver()
        initListeners()
    }

    private fun initListeners() {
        binding.fabNewProduct.setOnClickListener {
            val nextScreen = Intent(this, NewProductActivity::class.java)
            newProductRequest.launch(nextScreen)
        }
    }

    private fun initObserver() {
        mainViewModel.products.observe(this) { products ->
            products?.let { mainListAdapter.submitList(products) }
        }
    }

    private fun setUpRecyclerView() {
        with(binding) {
            rvProducts.adapter = mainListAdapter
            rvProducts.layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }


}