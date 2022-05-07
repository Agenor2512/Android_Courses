package fr.agenor.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

// Create the view with onCreate()
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton: Button = findViewById(R.id.button)
        rollButton.setOnClickListener { diceRoll() }

        // Do a random dice roll to display the dice image when the app start
        // The boolean prevent the display of the toasts
        diceRoll(false)
    }

    // Function to roll the dice
    private fun diceRoll(mustDisplayToast: Boolean = true) {
        val dice = Dice(6)
        val rollResult = dice.roll()
        val luckyNumber = 5
        // Find the ImageView in the layout
        val diceImage: ImageView = findViewById(R.id.imageView)

        // Set the dices images depending on the roll result
        // Warning : It's necessary to use an 'else' branch with a 'when' bc any cases should be covered
        val drawableResource = when (rollResult) {
            1 ->  R.drawable.dice_1
            2 ->  R.drawable.dice_2
            3 ->  R.drawable.dice_3
            4 ->  R.drawable.dice_4
            5 ->  R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        // Update the ImageView with the correct drawable resource
        diceImage.setImageResource(drawableResource)

        // Update the content description
        // The Screen readers can use content description to read out loud
        diceImage.contentDescription = rollResult.toString()

        if (mustDisplayToast) {
            // Displaying toasts
            if (rollResult == luckyNumber) {
                Toast.makeText(this, "Congrats ! It's the lucky number.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "You didn't win. Try again !", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

// Dice class
class Dice(private val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}
