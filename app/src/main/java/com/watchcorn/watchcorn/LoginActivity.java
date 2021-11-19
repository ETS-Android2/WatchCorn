package com.watchcorn.watchcorn;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LoginActivity extends AppCompatActivity {

    private EditText email, pass;
    private Button login, signin;
    private DB database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.EmailAddress);
        pass = findViewById(R.id.Password);
        login = findViewById(R.id.Login);
        signin = findViewById(R.id.SignIn);

        database = new DB(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = database.GetData();

                String emailString = email.getText().toString();
                emailString = emailString.toLowerCase(Locale.ROOT);
                String passString = pass.getText().toString();

                String A = "je m'appelle oussama", B = "Je suis etudiant à l'ensao", C = "mon numero est : pourquoi tu veux savoi rmon num";

                while (res.moveToNext()) {
                    if (emailString.equals(res.getString(0)) && passString.equals(res.getString(1))) {
                        A = res.getString(0);
                        B = res.getString(1);
                        C = res.getString(3);
                    }
                }

                if (emailString.equals(A) && passString.equals(B)) {
                    if (C.equals("1")) {
                        Intent i = new Intent(LoginActivity.this, Test.class);
                        startActivity(i);
                        finish();
                    } else {
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();
                        database.Update(emailString);
                    }
                } else if (emailString.isEmpty() || passString.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setCancelable(true);
                    builder.setTitle("Warning !");
                    builder.setMessage("All fields are required ");
                    builder.show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setCancelable(true);
                    builder.setTitle("Warning !");
                    builder.setMessage("Email or Password is incorrect");
                    builder.show();
                }
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}