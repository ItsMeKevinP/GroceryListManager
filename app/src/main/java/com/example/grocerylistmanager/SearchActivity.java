package com.example.grocerylistmanager;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class SearchActivity extends AppCompatActivity {

    Intent intent;
    Button returnBut;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
        intent = getIntent();
        Toast toast = Toast.makeText(this, intent.getStringExtra("SearchName"), Toast.LENGTH_LONG);
        toast.show();

        returnBut = findViewById(R.id.returnBut);

        returnBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
