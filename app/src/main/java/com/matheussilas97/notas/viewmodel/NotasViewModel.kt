package com.matheussilas97.notas.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.matheussilas97.notas.data.Database
import com.matheussilas97.notas.data.GestorDeNotas
import com.matheussilas97.notas.data.Nota

class NotasViewModel(val gestorNotas: GestorDeNotas) : ViewModel() {


    private var mNotas: MutableLiveData<MutableList<Nota>>? = null

    fun getNotas(): LiveData<MutableList<Nota>> {
        if (mNotas == null) {
            mNotas =gestorNotas.getNotas()
            }
        return mNotas!!
    }

    fun save(mNota: Nota){
        gestorNotas.addNota(mNota)
    }
}