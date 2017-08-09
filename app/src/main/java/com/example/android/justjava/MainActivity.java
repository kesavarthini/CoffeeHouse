package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import java.text.NumberFormat;

import static android.R.attr.name;
import static android.R.attr.order;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    /** int quantity = 3;
     * This method is called when the order button is clicked.
     */ int quantity =3;
    public int calculatePrice(boolean haswhippedCream,boolean haschocolate) {
        int baseprice=5;
        if(haswhippedCream)
        {
            baseprice=baseprice+1;
        }
        if(haschocolate)
        {
            baseprice=baseprice+2;
        }
        return quantity * baseprice;
    }

    public void submitOrder(View view) {
        EditText editname=(EditText)findViewById((R.id.name_field));
        String name=editname.getText().toString();
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkBox);
        boolean haswhippedCream=whippedCreamCheckBox.isChecked();
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkBox);
        boolean haschocolate=chocolateCheckBox.isChecked();
        int price = calculatePrice(haswhippedCream,haschocolate);
        String priceMessage="Total : $" + price;
        priceMessage = priceMessage + " \n Thank you ";

        String orderSummary = createOrderSummary(price,haswhippedCream,haschocolate,name);

            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_SUBJECT,"Just Java order for "+name);
            intent.putExtra(Intent.EXTRA_TEXT,orderSummary);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }



    private String createOrderSummary(int number,boolean haswhippedcream,boolean haschocolate,String name)
    {
        String priceMessage = "Name: "+name;
        priceMessage = priceMessage + "\nQuantity:" + quantity + "\nAdd Whipped Cream? " + haswhippedcream;
        priceMessage += "\nAdd Chocolate? " + haschocolate;
        priceMessage = priceMessage + "\nTotal:$" + number + "\nThank you!";
        return ( priceMessage);
    }
public void increment(View View){
    quantity=quantity+1;
    display(quantity);

}

    public void decrement(View View) {

        if(quantity>0) {
            quantity = quantity - 1;
            display(quantity);
        }
        else{
            quantity=0;
        display(quantity);}
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

}