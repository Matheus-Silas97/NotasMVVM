package com.matheussilas97.notas.di

import com.matheussilas97.notas.data.Database
import com.matheussilas97.notas.data.GestorDeNotas
import com.matheussilas97.notas.viewmodel.NotasViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

object ModulosDeDependencia {

    val moduloDaApp = module {
        single { Database() }

        factory { GestorDeNotas(get()) }

        viewModel { NotasViewModel(get()) }
    }
}