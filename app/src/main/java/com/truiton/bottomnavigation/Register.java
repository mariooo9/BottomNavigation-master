package com.truiton.bottomnavigation;

/**
 * Created by jtibrewal on 25/03/17.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends Activity {

    EditText name, username,pswrd;
    Button reg,login;
    String str,str1,str2;
    DBAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name=(EditText) findViewById(R.id.name);
        username=(EditText) findViewById(R.id.email);
        pswrd=(EditText) findViewById(R.id.password);
        reg=(Button) findViewById(R.id.btnRegister);
        login=(Button) findViewById(R.id.btnLinkToLoginScreen);
        db = new DBAdapter(this.getApplicationContext());

        reg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                str = name.getText().toString().trim();
                str1 = username.getText().toString().trim();
                str2 = pswrd.getText().toString().trim();
                if (!str.equals("") && !str1.equals("") && !str2.equals("")) {
                    db.insert(str, str1, str2);
                    Toast.makeText(getBaseContext(), "Registeration Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Register.this, Login.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Please enter your details!", Toast.LENGTH_LONG)
                            .show();
                }

                // TODO Auto-generated method stub

            }
        });


        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });

    }
}
