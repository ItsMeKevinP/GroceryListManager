package com.example.grocerylistmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    RecyclerView listView;
    RecycleAdapter recAdapter;

    ArrayList<String> titles = new ArrayList<>();
    Button but;
    EditText newListName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titles.add("Stop n Shop");
        DisplayMetrics dm =  this.getResources().getDisplayMetrics();
        EditText nameField = findViewById(R.id.listNameField);
        listView = findViewById(R.id.recyclerViewList);

        nameField.setWidth(dm.widthPixels-nameField.getWidth()-500);

        listView.setMinimumHeight(dm.heightPixels-nameField.getHeight());
        listView.setLayoutManager(new LinearLayoutManager(this));

        recAdapter = new RecycleAdapter(this, titles);
        listView.setAdapter(recAdapter);

        titles.add("Western Beef");
        newListName = findViewById(R.id.listNameField);


        but = findViewById(R.id.addButton);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(newListName.getText() != null){
                    titles.add(newListName.getText().toString());
                    newListName.getText().clear();
                    recAdapter.notifyDataSetChanged();
                }
            }
        });

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.navigateList:
                Intent intent = new Intent(this, listItem.class);
                intent.putExtra("Title",titles.get(item.getGroupId()));
                startActivity(intent);
                return true;
            case R.id.renameList:
                renameItem(item, item.getGroupId());
                return true;
            case R.id.deleteList:
                recAdapter.remove(item.getGroupId());
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void renameItem(MenuItem item, int pos){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Enter New Name");
        final int tempPos = pos;
        final EditText newName = new EditText(this);
        alert.setView(newName);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                recAdapter.renameItem(newName.getText().toString(), tempPos);
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