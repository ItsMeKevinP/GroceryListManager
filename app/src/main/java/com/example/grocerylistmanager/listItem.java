package com.example.grocerylistmanager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class listItem extends AppCompatActivity {

    Intent intent;

    RecyclerView itemRecyclerView;
    RecycleAdapterList recycleAdapterList;
    ConstraintLayout bottomLayout;

    Button removeChecks, searchButton;

    Context context;

    ArrayList<String> brands = new ArrayList<>();
    ArrayList<String> types = new ArrayList<>();
    ArrayList<Float> quantities = new ArrayList<>();
    ArrayList<String> units = new ArrayList<>();
    ArrayList<Boolean> booleans = new ArrayList<>();



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item_activity);

        intent = getIntent();

        context = this;

        setTitle(intent.getStringExtra("Title"));
        DisplayMetrics dm =  this.getResources().getDisplayMetrics();

        itemRecyclerView = findViewById(R.id.itemRecyclerVewList);
        bottomLayout = findViewById(R.id.item_bottom_layout);


        //populate each array with the appropriate values in the JSON file
        brands.add("Brand 1");
        types.add("Type 2");
        quantities.add(3.0f);
        units.add("pc");
        booleans.add(false);
        //------------------------------------------------------------------

        itemRecyclerView.setMinimumHeight(dm.heightPixels-bottomLayout.getHeight());

        removeChecks = findViewById(R.id.removeCheckBut);
        searchButton = findViewById(R.id.itemSearchBut);


        itemRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        recycleAdapterList = new RecycleAdapterList(this, brands, types, quantities, units, booleans);

        itemRecyclerView.setAdapter(recycleAdapterList);

        removeChecks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeChecks();
                recycleAdapterList.notifyDataSetChanged();
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem();
            }
        });
    }
    //reset all booleans
    public void removeChecks(){
        for(Boolean i : booleans)
            i = false;

    }

    public void addItem(){

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Enter Item To Search");
        final EditText searchName = new EditText(this);
        alert.setView(searchName);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Intent intent = new Intent(context, SearchActivity.class);
                intent.putExtra("SearchName", searchName.getText().toString());
                startActivityForResult(intent, 1);

            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alert.show();

    }


}
