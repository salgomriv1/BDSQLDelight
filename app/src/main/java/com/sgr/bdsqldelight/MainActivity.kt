package com.sgr.bdsqldelight

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.sgr.dbsqldelight.Database
import com.sgr.dbsqldelight.db.Usuarios
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


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

        //Se crea una instancia de la interfaz
        val listener = object: DatabaseListener {
            override fun onSuccess() {
                println("Se realizo la operacion con exito")
            }

            override fun onError(error: String) {
                println("Se produjo un error durante la operacion: $error")
            }

        }

        //Se ejecuta la funcion de insertar usuario
        //insertarUsuario("Ramon", "ramon@gmail.com", listener)

        //Se ejecuta la funcion de borrar usuario
        //borrarUsuario(1, listener)

        //Se ejecuta la funcion de actualizar usuario
        //actualizarUsuario(2, "Ramiro", "ramiro@gmail.com", listener)

        //Se ejecuta la funcion de recuperar un usuario con una corutina
        database.transaction {
            GlobalScope.launch {
                val usuario : Usuarios = database.setupdbQueries.obtenerUsuario(2).executeAsOne()
                println(usuario.nombre + " " + usuario.email)
            }
        }
    }

    //Se realiza una funcion para insertar usuarios con un listener que informara del exito
    fun insertarUsuario(nombre: String, email: String, listener: DatabaseListener) {
        try {
            database.setupdbQueries.InsertarUsuario(nombre, email)
            listener.onSuccess()
        }catch (e: Exception) {
            listener.onError(e.message ?: "Error desconocido")
        }
    }

    //Se realiza una funcion para borrar usuarios con un listener que informara del exito
    fun borrarUsuario(id: Long, listener:DatabaseListener) {
        try {
            database.setupdbQueries.BorrarUsuarioPorId(id)
            listener.onSuccess()
        }catch(e: Exception) {
            listener.onError(e.message ?: "Error desconocido")
        }
    }

    //Se realiza una funcion para actualizar usuarios con un listener que informara del exito
    fun actualizarUsuario(id: Long, nombre: String, mail: String, listener: DatabaseListener){

        try {
            database.setupdbQueries.actualizarUsuarioPorId(nombre, mail, id)
            listener.onSuccess()
        }catch (e: Exception) {
            listener.onError(e.message ?: "Error descnocido")
        }
    }
}