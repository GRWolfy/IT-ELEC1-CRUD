package com.example.it_elec1_crud;

import  androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.*;
import android.view.*;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

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
        main_layout = findViewById(R.id.main_layout);
        lstNames = findViewById(R.id.lstNames);

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
    EditText editText_Firstname, editText_Middlename, editText_Lastname;
    Button btnAdd, btnUpdate, btnDelete;
    ListView lstNames;
    LinearLayout main_layout;
    String Firstname = "";
    String Middlename = "";
    String Lastname = "";
    String Fullname = "";
    int index = 0;

    ArrayList<String> arrNames;
    ArrayAdapter<String> namesAdapter;

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnAdd:{
                String fullname = editText_Firstname.getText().toString() + " " + editText_Middlename.getText().toString() + " " + editText_Lastname.getText().toString();
                arrNames.add(fullname);
                namesAdapter.notifyDataSetChanged();
                //createsnackbar
            }
            break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Fullname = arrNames.get(i);
        //createSnackbar(arrNames.get(i), true);
        getFullname();
        setEditText();
        setIndex(i);
    }

    private void createSnackbar(String message, boolean valid) {
        Snackbar snackbar = Snackbar.make(main_layout, message, Snackbar.LENGTH_SHORT);
        View viewSnackbar = snackbar.getView();

        if(valid)
        {
            viewSnackbar.setBackgroundColor(getResources().getColor(R.color.purple_700));
        }
        else
        {
            viewSnackbar.setBackgroundColor(getResources().getColor(R.color.black));
        }

        snackbar.show();
    }

    private void getFullname(){
        ArrayList<String> lstName = new ArrayList<String>();
        String[] newStr = Fullname.split(" ");

        for(String s : newStr){
            lstName.add(s);
        }

        for(int i = 0; i < lstName.size(); i++){
            if (i == lstName.size() - 1){
                Lastname = lstName.get(i);
            }
            else if (i == lstName.size() - 2){
                Middlename = lstName.get(i);
            }
            else {
                Firstname += lstName.get(i) + " ";
            }
        }
    }
    private void setEditText(){
        editText_Firstname.setText(Firstname);
        editText_Middlename.setText(Middlename);
        editText_Lastname.setText(Lastname);
    }

    private int setIndex(int i){
        return index = i;
    }
}