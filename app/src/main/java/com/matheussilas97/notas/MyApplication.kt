package com.matheussilas97.notas

import android.app.Application
import com.matheussilas97.notas.di.ModulosDeDependencia
import org.koin.android.ext.android.startKoin

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(ModulosDeDependencia.moduloDaApp))
    }
}