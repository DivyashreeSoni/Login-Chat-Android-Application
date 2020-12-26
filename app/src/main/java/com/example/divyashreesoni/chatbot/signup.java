package com.example.divyashreesoni.chatbot;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signup extends AppCompatActivity {

    DatabaseHelper myDb;
    Button signin, viewdata, login;
    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        myDb = new DatabaseHelper(this);
        username = (EditText) findViewById(R.id.e1);
        password = (EditText) findViewById(R.id.e2);
        signin = (Button) findViewById(R.id.b1);
        viewdata = (Button) findViewById(R.id.b2);
        login = (Button) findViewById(R.id.b3);
        AddData();
        verifyData();
        viewAll();

    }

    public void AddData(){
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = username.getText().toString();
                String passkey = password.getText().toString();
                if(phone.isEmpty() || passkey.isEmpty())
                {
                    Toast.makeText(signup.this, "Enter Credentials", Toast.LENGTH_LONG).show();
                }
                else {
                    boolean checkUser = myDb.checkUsername(phone);
                    if(checkUser) {
                        boolean isInserted = myDb.insertData(phone, passkey);
                        if (isInserted) {
                            Toast.makeText(signup.this, "Registration Successful", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(signup.this, MainActivity.class);
                            startActivity(i);
                        }
                    }
                    else{
                        Toast.makeText(signup.this, "This username already exists", Toast.LENGTH_LONG).show();
                    }
                }
                username.setText("");
                password.setText("");
            }
        });
    }

    public void verifyData(){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = username.getText().toString();
                String passkey = password.getText().toString();
                if(phone.isEmpty() || passkey.isEmpty())
                {
                    Toast.makeText(signup.this, "Enter Credentials", Toast.LENGTH_LONG).show();
                }
                else {
                    boolean data = myDb.check(phone, passkey);
                    if(data) {
                            Intent i = new Intent(signup.this, MainActivity.class);
                            startActivity(i);
                    }
                    else{
                        Toast.makeText(signup.this, "Your password is incorrect", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
    }


    public void viewAll() {
        viewdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getAllData();
                if (res.getCount() == 0) {
                    showMessage("Error", "Nothing Found");
                    return;
                }

                StringBuffer buff = new StringBuffer();
                while (res.moveToNext()) {
                    buff.append("id : " + res.getString(0) + "\n");
                    buff.append("Username: " + res.getString(1) + "\n");
                    buff.append("Password: " + res.getString(2) + "\n");
                }

                showMessage("Data", buff.toString());
            }
        });
    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder build = new AlertDialog.Builder(this);
        build.setCancelable(true);
        build.setTitle(title);
        build.setMessage(Message);
        build.show();
    }

}


