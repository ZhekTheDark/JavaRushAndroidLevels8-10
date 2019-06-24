package com.example.javarushlevels8_10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.View
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
        numberOfCoffees++
        displayQuantity(numberOfCoffees)
    }

    fun decrement(view: View) {
        numberOfCoffees--
        displayQuantity(numberOfCoffees)
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
    }

    fun createOrderSummary(a: Number) {
        order_summary_text_view.text =
                    "\nName: Eugene Bobrutskov" +
                    "\nQuantity: $a" +
                    "\nTotal: $price" +
                    "\nThank you!"
    }

}