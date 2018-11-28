package com.example.bpn18.cosmos;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by bpn18 on 9/24/2017.
 */

public class loginActivity extends AppCompatActivity {
Button login,register;
    EditText username,password;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        preferences = getSharedPreferences("userinfo",0);
        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.Password);
        login=(Button)findViewById(R.id.login);
        register=(Button)findViewById(R.id.register);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameValue = preferences.getString("username", "");
                String passwordValue = preferences.getString("password", "");

                if (usernameValue.equals(username.getText().toString()) && passwordValue.equals(password.getText().toString())) {
                    Toast.makeText(loginActivity.this, "Welcome " + usernameValue +" :)", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(loginActivity.this, MainSlider12345.class);
                    startActivity(intent);
                    password.setText("");
                } else {
                    Toast.makeText(loginActivity.this, "login failure", Toast.LENGTH_SHORT).show();
                }



            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginActivity.this, RegisterUser.class);
                startActivity(intent);
            }
        });

}
}

