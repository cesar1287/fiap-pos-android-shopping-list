package com.github.cesar1287.lembretedecompras.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.cesar1287.lembretedecompras.R
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

    private fun dialogDelete(): AlertDialog {
        return AlertDialog.Builder(this)
            .setTitle("Lembrete de Compras")
            .setMessage("Deseja apagar sua lista?")
            .setIcon(R.drawable.ic_delete)
            .setPositiveButton("Apagar") { dialog, _ ->
                mainViewModel.deleteAll()
                dialog.dismiss()
            }
            .setNegativeButton("Cancelar") {
                    dialog, _ -> dialog.dismiss()
            }
            .create()
    }


    //Método para criar o menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }


    //Listener para escutar o clique no botão
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.btDelete -> {
                dialogDelete().show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}