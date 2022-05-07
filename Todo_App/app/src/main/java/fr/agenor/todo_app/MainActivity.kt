package fr.agenor.todo_app

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    private var clickCounter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpUi()
    }

    private fun setUpUi() {
        val textField: TextView = findViewById(R.id.textView)
        textField.text = getString(R.string.clicked_counter, clickCounter)
        val addButton: Button = findViewById(R.id.add_button)
        addButton.setOnClickListener {
            clickCounter++
            textField.text = getString(R.string.clicked_counter, clickCounter)

            if (clickCounter == 10) {
                // Toast.makeText(this, R.string.you_reached_100, Toast.LENGTH_SHORT).show()
                AlertDialog.Builder(this).also {
                    it.setMessage(R.string.you_reached_100)
                    it.setNeutralButton(R.string.dismiss,
                            DialogInterface.OnClickListener { dialog, id ->
                                dialog.dismiss()
                            }
                        )
                    it.create().show()
                }
            }
        }

        val removeButton: Button = findViewById(R.id.remove_button)
        removeButton.setOnClickListener {
            clickCounter--
            textField.text = getString(R.string.clicked_counter, clickCounter)
        }
        // TODO : Ajouter un bouton clear
    }
}