package com.example.android.justjava;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    private int quantity = 0;
    private final int price = 5;
    private int totalPrice = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.favorite:
//                submitOrder();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

//        return super.onOptionsItemSelected(item);
    }

    public void decrement(View view){
        quantity--;
        totalPrice = price * quantity;
        display(quantity);
//        displayPrice(totalPrice);
    }

    public void increment(View view){
        quantity++;
        totalPrice = price * quantity;
        display(quantity);
//        displayPrice(totalPrice);
    }

    public void submitOrder(View view){
        CheckBox whipppedCream = (CheckBox) findViewById(R.id.whipped_cream);
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate);
        EditText name = (EditText) findViewById(R.id.customer_name);
        String customerName = name.getText().toString();
        boolean hasWhippedCream = whipppedCream.isChecked();
        boolean hasChocolate = chocolate.isChecked();
        TextView summaryTextView = (TextView) findViewById(R.id.summary_text_view);
        totalPrice = price * quantity;
        summaryTextView.setText(createOrderSummary(totalPrice, hasWhippedCream, hasChocolate, customerName));
    }

    public void resubmitOrder(MenuItem item){
        Intent intent = new Intent(this, Order.class);
        startActivity(intent);
    }

    private String createOrderSummary(int price, boolean whippedCream, boolean chocolate, String name){
        String result = "Name: " + name;
        result += "\nQuantity: " + quantity;
        if (whippedCream){
            result += "\nWhipped Cream: True";
        } else {
            result += "\nWhipped Cream: False";
        }
        if (chocolate){
            result += "\nChocolate: True";
        } else {
            result += "\nChocolate: False";
        }
        result += "\nTotal: $" + price;
        result += "\nThank you!";
        return result;

    }



    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayPrice(String message){
        TextView summaryTextView = (TextView) findViewById(R.id.summary_text_view);
        summaryTextView.setText(message);
    }


}
