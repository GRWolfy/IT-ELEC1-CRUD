package com.example.it_elec1_crud;

import  androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.*;
import android.view.*;

import com.example.it_elec1_crud.Models.Name;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        //Data Bindings
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        editText_Firstname = findViewById(R.id.editText_FirstName);
        editText_Middlename = findViewById(R.id.editText_MiddleName);
        editText_Lastname = findViewById(R.id.editText_LastName);
        main_layout = findViewById(R.id.main_layout);
        lstNames = findViewById(R.id.lstNames);

        name = new Name();
        arrNames = new ArrayList<>();
        namesAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, arrNames);
        lstNames.setAdapter(namesAdapter);

        //OnCLick Listener
        btnAdd.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        lstNames.setOnItemClickListener(this);
    }

    //Declarations
    Name name;
    EditText editText_Firstname, editText_Middlename, editText_Lastname;
    Button btnAdd, btnUpdate, btnDelete;
    ListView lstNames;
    LinearLayout main_layout;

    boolean checker = false;
    ArrayList<String> arrNames;
    ArrayAdapter<String> namesAdapter;

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnAdd:
                arrNames.add(insertFullname());
                namesAdapter.notifyDataSetChanged();
                clearEditText();
                createSnackbar("Name added to list.", true);
                break;

            case R.id.btnUpdate:
                arrNames.set(name.getIndex(), insertFullname());
                namesAdapter.notifyDataSetChanged();
                clearEditText();
                createSnackbar("Name updated.", true);
                break;

            case R.id.btnDelete:
                if(arrNames.size() >= 0 && checker == true){
                    arrNames.remove(name.getIndex());
                    namesAdapter.notifyDataSetChanged();
                    clearEditText();
                    createSnackbar("Name deleted.", true);
                    checker = false;
                }
                else {
                    createSnackbar("Empty list view.", false);
                    name.setIndex(0);
                }

                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        name.setFullname(arrNames.get(i));
        clearEditText();
        insertName();
        setEditText();
        name.setIndex(i);
        checker = true;
    }

    private void createSnackbar(String message, boolean valid) {
        Snackbar snackbar = Snackbar.make(main_layout, message, Snackbar.LENGTH_SHORT);
        View viewSnackbar = snackbar.getView();

        if(valid) {
            viewSnackbar.setBackgroundColor(getResources().getColor(R.color.purple_700));
        }
        else {
            viewSnackbar.setBackgroundColor(getResources().getColor(R.color.design_default_color_error));
        }

        snackbar.show();
    }

    private void insertName(){
        ArrayList<String> lstName = new ArrayList<String>();
        String[] newStr = name.getFullname().split(" ");
        String tempFirstname = "";

        for(String s : newStr){
            lstName.add(s);
        }

        for(int i = 0; i < lstName.size(); i++){
            if (i == lstName.size() - 1){
                name.setLastname(lstName.get(i));
            }
            else if (i == lstName.size() - 2){
                name.setMiddlename(lstName.get(i) + " ");
            }
            else {
                tempFirstname += lstName.get(i) + " ";
            }
        }

        name.setFirstname(tempFirstname);
    }

    private void setEditText(){
        editText_Firstname.setText(name.getFirstname());
        editText_Middlename.setText(name.getMiddlename());
        editText_Lastname.setText(name.getLastname());
    }

    private void clearEditText(){
        editText_Firstname.setText("");
        editText_Middlename.setText("");
        editText_Lastname.setText("");
    }

    private String insertFullname(){
        return editText_Firstname.getText().toString() + " " + editText_Middlename.getText().toString() + " " + editText_Lastname.getText().toString();
    }
}