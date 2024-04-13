package com.sgr.bdsqldelight

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.sgr.dbsqldelight.Database

class MainActivity : AppCompatActivity() {

    val database = Database(AndroidSqliteDriver(Database.Schema, this, "Database.db"))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //database.setupdbQueries.InsertarUsuario("pedro", "pedro@gmail.com")
        //database.setupdbQueries.InsertarProducto("galletas", 56.5)


    }
}