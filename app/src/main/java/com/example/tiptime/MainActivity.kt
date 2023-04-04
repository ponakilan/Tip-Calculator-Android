package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.lang.Double
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Calculating the tip on click of the button
        binding.calculateTipButton.setOnClickListener { calculateTip() }
    }

    /**
     * Calculate the tip amount and update the view with the tip amount
     */
    private fun calculateTip() {
        val cost = binding.costOfService.text;
        if (cost.isEmpty()) {
            Snackbar.make(binding.scrollView, "Enter some amount", Snackbar.LENGTH_SHORT).show();
            return;
        }

        // Getting the selected tip percentage
        val tipPercentage = when(binding.tipOptions.checkedRadioButtonId) {
            R.id.amazing_service_radio -> 0.2
            R.id.good_service_radio -> 0.18
            else -> 0.15
        }

        // Calculating the tip and rounding it off if the round off switch is toggled
        var tipAmount = tipPercentage * cost.toString().toDouble()
        if (binding.roundUpSwitch.isChecked) { tipAmount = kotlin.math.ceil(tipAmount) }

        // Formatting the tip amount based on local currency and updating the view with the final tip amount
        binding.tipAmount.text = getString(R.string.calculated_tip_amount, NumberFormat.getCurrencyInstance().format(tipAmount))
    }
}