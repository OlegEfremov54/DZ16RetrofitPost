package com.example.dz16retrofitpost

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {

    private lateinit var dogIV: ImageView
    private lateinit var dogBTN: Button
    private lateinit var loadingPB: ProgressBar
    private lateinit var toolbarMain: Toolbar
    private lateinit var mainViewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //Тулбар
        toolbarMain = findViewById(R.id.toolbarMain)
        setSupportActionBar(toolbarMain)
        title = "  Сабакен"
        toolbarMain.subtitle = "Версия 1."
        toolbarMain.setLogo(R.drawable.round_pest_control_rodent_24)

        mainViewModel = ViewModelProvider(owner = this).get(MyViewModel::class.java)

        dogIV = findViewById(R.id.iv_dog)
        dogBTN = findViewById(R.id.btn_dog)
        loadingPB = findViewById(R.id.loading_pb)
        observe()
        dogBTN.setOnClickListener {
            loadingPB.visibility = View.VISIBLE
            mainViewModel.updateDog() }
    }

    private fun observe() {
        mainViewModel.imageUrl.observe(this) {
            Glide.with(this).load(it).into(dogIV)
            loadingPB.visibility = View.GONE
        }
    }


    // Активация Меню
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.infoMenuMain -> {
                Toast.makeText(applicationContext, "Автор Ефремов О.В. Создан 29.11.2024",
                    Toast.LENGTH_LONG).show()
            }
            R.id.exitMenuMain ->{
                Toast.makeText(applicationContext, "Работа приложения завершена",
                    Toast.LENGTH_LONG).show()
                finishAffinity()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}