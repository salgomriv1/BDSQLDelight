package com.sgr.bdsqldelight

//Se define una interfaz para hacer un listener que maneje los resultados de operaciones en la db
interface DatabaseListener {
    fun onSuccess()
    fun onError(error: String)
}