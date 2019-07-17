package com.example.javarushlevels8_10

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

//import android.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    //val valueOfCoffees = value_of_quantity_text_view.text
    //var numberOfCoffees = valueOfCoffees.toString().toInt()
    var numberOfCoffees = 1
    var price = 5

    fun increment(view: View) {
        if (numberOfCoffees == 100) Toast.makeText(
            this,
            "Нельзя заказать больше ста чашек кофе",
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
            "Нельзя заказать меньше одной чашки кофе",
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
        //displayPrice(price)
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
            "Name: " +
                    if (name_checkbox.text.isEmpty()) "Eugene Bobrutskov "
                    else name_checkbox.text
        orderSummaryText += "\n"
        orderSummaryText +="Add whipered cream? " +
                    if (cream_checkbox.isChecked) "true \n"
                    else "false \n"
        orderSummaryText +="Add chocolate? " +
                if (chocolate_checkbox.isChecked) "true \n"
                else "false \n"
        orderSummaryText +=
            "Quantity: $a \n" +
            "Total: $price \n" +
            "Thank you! \n"

        //order_summary_text_view.text = orderSummaryText

        val recipientMessage:Array<String> = arrayOf("Zhekzerg@gmail.com")

        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_EMAIL, recipientMessage)
            putExtra(Intent.EXTRA_SUBJECT, "Just Java order")
            putExtra(Intent.EXTRA_TEXT, orderSummaryText)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }

    }

}