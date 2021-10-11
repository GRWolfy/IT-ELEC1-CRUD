package com.example.it_elec1_crud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.*;
import android.view.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Data Bindings
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        editText_Firstname = findViewById(R.id.editText_FirstName);
        editText_Middlename = findViewById(R.id.editText_MiddleName);
        editText_Lastname = findViewById(R.id.editText_LastName);

        //OnCLick Listener
        btnAdd.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }

    //Declarations
    EditText editText_Firstname, editText_Middlename, editText_Lastname;
    Button btnAdd, btnUpdate, btnDelete;

    @Override
    public void onClick(View view) {

    }
}