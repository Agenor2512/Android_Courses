package fr.agenor.tiptime

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import fr.agenor.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat.*
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener {
            (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
                binding.root.windowToken,
                0
            )

            calculateTip()
        }
    }

    private fun calculateTip() {
        val stringInTextField = binding.costOfServiceText.text.toString()
        val toastText = R.string.error_empty_cost

        // If the calculate button is tap but there's no cost -> display a toast and clear the tip amount, then, make a return
        if (stringInTextField.isEmpty()) {
            Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show()
            binding.tipResult.text = ""
            return
        }
        val cost = stringInTextField.toDouble()

        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.twenty_percent_option -> 0.20
            R.id.eighteen_percent_option -> 0.18
            else -> 0.15
        }

        var tip = tipPercentage * cost
        if (binding.roundUpSwitch.isChecked) {
            tip = ceil(tip)
        }

        val formattedTip = getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}