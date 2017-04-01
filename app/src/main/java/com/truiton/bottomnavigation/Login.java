package com.truiton.bottomnavigation;

/**
 * Created by jtibrewal on 25/03/17.
 */

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {

    EditText user, pass;
    Button login, signup;
    String st1, st2;
    DBAdapter db;


    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        //Toast.makeText(this, "Click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 10);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DBAdapter(this.getApplicationContext());

        user = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.btnLogin);
        signup = (Button) findViewById(R.id.btnLinkToRegisterScreen);

        login.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                st1 = user.getText().toString().trim();
                st2 = pass.getText().toString().trim();
                if (!st1.equals("") && !st2.equals("")) {
                    // login user
                    if(db.checkUser(st1, st2)) {
                        //Toast.makeText(getApplicationContext(), "VALID USER", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getBaseContext(),MainActivity.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(getApplicationContext(), "INVALID USER", Toast.LENGTH_SHORT).show();
                    }
                    //showDataOnToast();
                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext(),
                            "Please enter the credentials!", Toast.LENGTH_LONG)
                            .show();
                }

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
    }

    public void showDataOnToast() {
        // TODO Auto-generated method stub
        Cursor c = db.getInsertedData();
        if (c != null && c.moveToFirst()) // return true value if data is there
        {
            do {
                Toast.makeText(
                        this,
                        "Id: " + c.getString(0) + "\n" + "Name: "
                                + c.getString(1) + "\n" + "UserName: "
                                + c.getString(2) + "\n" + "Password: "
                                + c.getString(3), Toast.LENGTH_LONG).show();
            } while (c.moveToNext());
        } else
            Toast.makeText(getBaseContext(), "Data not Registered",
                    Toast.LENGTH_SHORT).show();
        db.close();
    }

}

