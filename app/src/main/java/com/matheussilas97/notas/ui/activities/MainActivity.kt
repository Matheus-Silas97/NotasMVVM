package com.matheussilas97.notas.ui.activities

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.matheussilas97.notas.NotasAdapter
import com.matheussilas97.notas.R
import com.matheussilas97.notas.data.Nota
import com.matheussilas97.notas.viewmodel.NotasViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_add.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var notasAdapter: NotasAdapter
    private val notasViewModel: NotasViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        //notasViewModel = ViewModelProvider(this)[NotasViewModel::class.java]

        notasViewModel.getNotas().observe(this, Observer { data ->
            data?.let {
                if (it.isEmpty()) {
                    Toast.makeText(this, "Lista vazia", Toast.LENGTH_SHORT).show()
                } else {
                    notasAdapter.addAll(it)
                }
            }
        })


        recycler_view.adapter = notasAdapter
        recycler_view.layoutManager = LinearLayoutManager(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_add) {
            dialogAddNote()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun dialogAddNote() {
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_add, null, false)
        val editNota = view.edit_note.text.toString()
        val dialog = AlertDialog.Builder(this)
        dialog.setView(view)
        dialog.setNegativeButton("Cancelar", null)
        dialog.setPositiveButton("Salvar") { dialogInterface: DialogInterface, i: Int ->
            val note = Nota(0, editNota)

            notasViewModel.save(note)
        }
        dialog.create().show()

    }
}