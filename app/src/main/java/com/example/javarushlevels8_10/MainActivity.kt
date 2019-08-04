package com.example.javarushlevels8_10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    var numberOfCoffees = 1
    var price = 5

    fun increment(view: View) {
        if (numberOfCoffees == 100) Toast.makeText(
            this,
            getString(R.string.more_cups),
            Toast.LENGTH_SHORT
        ).show()
        else {
            numberOfCoffees++
            displayQuantity(numberOfCoffees)
        }
    }

    fun decrement(view: View) {
        if (numberOfCoffees == 1) Toast.makeText(
            this,
            getString(R.string.less_than_one_cup),
            Toast.LENGTH_SHORT
        ).show()
        else {
            numberOfCoffees--
            displayQuantity(numberOfCoffees)
        }
    }

    /**
     * This method is called when the order button is clicked.
     */
    fun submitOrder(view: View) {
        displayQuantity(numberOfCoffees)
        calculatePrice(numberOfCoffees)
        createOrderSummary(numberOfCoffees)
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private fun displayQuantity(number: Int) {
        value_of_quantity_text_view.text = "$number"
    }

    /**
     * Calculates the price of the order.
     *
     * @param quantity is the number of cups of coffee ordered
     */
    private fun calculatePrice(quantity: Int) {
        price = quantity * 5
        if (cream_checkbox.isChecked) price += quantity * 1
        if (chocolate_checkbox.isChecked) price += quantity * 2
    }

    private fun createOrderSummary(a: Number) {
        var orderSummaryText =
            getString(R.string.order_summary_name, name_checkbox.text)
        orderSummaryText += "\n"
        orderSummaryText += getString(R.string.add_whipped_cream) +
                if (cream_checkbox.isChecked) " " + getString(R.string.True)
                else " " + getString(R.string.False)
        orderSummaryText += "\n"
        orderSummaryText += getString(R.string.add_chocolate) +
                if (chocolate_checkbox.isChecked) " " + getString(R.string.True)
                else " " + getString(R.string.False)
        orderSummaryText += "\n"
        orderSummaryText += getString(R.string.quantity_dots, a)
        orderSummaryText += "\n"
        orderSummaryText += getString(R.string.total_dots, price.toString())
        orderSummaryText += "\n"
        orderSummaryText += getString(R.string.thank_you)

        //val recipientMessage:Array<String> = arrayOf("")

        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_EMAIL, "")
            putExtra(Intent.EXTRA_SUBJECT, getString(R.string.subject, name_checkbox.text))
            putExtra(Intent.EXTRA_TEXT, orderSummaryText)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}