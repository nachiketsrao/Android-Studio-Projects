package com.example.todolist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText item;
    Button add;
    ListView listView;
    ArrayList<String> itemList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        item = findViewById(R.id.editText);
        add = findViewById(R.id.button);
        listView = findViewById(R.id.list);

        itemList = FileHelper.readData(this); //this will read data and send it to the arrayList if there is an data
        //FileHelper is the class I wrote, readData method returns the arraylist of existing items

        arrayAdapter = new ArrayAdapter<>(this
        , android.R.layout.simple_list_item_1, android.R.id.text1, itemList);

        listView.setAdapter(arrayAdapter); //the adapter connects the listView component to the ArrayList

        // to add items when 'ADD' button is clicked:
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String itemName = item.getText().toString();

                if (!itemName.equals("")) {
                    // if the editText is empty, nothing is added
                    itemList.add(itemName);
                }
                item.setText("");

                FileHelper.writeData(itemList, getApplicationContext());
                arrayAdapter.notifyDataSetChanged();

            }
        });

        // To give option to delete the item chosen:
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Delete");
                alert.setMessage("Do you want to delete this item from the list?");
                alert.setCancelable(false);
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel(); //alert dialog will close if the user clicks NO
                    }
                });
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        itemList.remove(position);
                        arrayAdapter.notifyDataSetChanged();
                        FileHelper.writeData(itemList, getApplicationContext());

                    }
                });

                AlertDialog alertDialog = alert.create();
                alertDialog.show();

            }
        });

    }
}