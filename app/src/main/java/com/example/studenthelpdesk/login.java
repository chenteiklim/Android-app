package com.example.studenthelpdesk;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {
    private EditText emailInput;
    private EditText passwordInput;
    private MyDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpage);

        dbHelper = new MyDBHelper(this); // Initialize your database helper

        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);

        Button loginButton = findViewById(R.id.loginBtn);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Collect user input
                String enteredEmail = emailInput.getText().toString();
                String enteredPassword = passwordInput.getText().toString();

                // Query the database to verify login credentials
                SQLiteDatabase db = dbHelper.getReadableDatabase();

                String[] projection = {MyDBHelper.KEY_ID};
                String selection = MyDBHelper.KEY_EMAIL + " = ? AND " + MyDBHelper.KEY_PASSWORD + " = ?";
                String[] selectionArgs = {enteredEmail, enteredPassword};

                Cursor cursor = db.query(MyDBHelper.TABLE_NAME, projection, selection, selectionArgs, null, null, null);

                if (cursor.moveToFirst()) {
                    // Login was successful, navigate to the main activity or another appropriate screen
                    Intent intent = new Intent(login.this, homepage.class);
                    startActivity(intent);
                } else {
                    // Login failed, display an error message
                    Toast.makeText(login.this, "Login failed. Please check your credentials.", Toast.LENGTH_SHORT).show();
                }

                // Close the cursor and the database
                cursor.close();
                db.close();
            }
        });
    }
}