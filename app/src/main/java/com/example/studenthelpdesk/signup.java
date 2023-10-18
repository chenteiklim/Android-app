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
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class signup extends AppCompatActivity {
    private EditText emailInput;
    private EditText passwordInput;
    private EditText confirmPasswordInput;
    private MyDBHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signuppage);

        dbHelper = new MyDBHelper(this);
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        confirmPasswordInput = findViewById(R.id.confirmInput);

        Button signUpButton = findViewById(R.id.signUpBtn);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailInput.getText().toString();
                String password = passwordInput.getText().toString();
                String confirmPassword = confirmPasswordInput.getText().toString();

                if (!email.contains("@")) {
                    // Email is not valid, show an error message
                    Toast.makeText(signup.this, "Invalid email format. Please enter a valid email address.", Toast.LENGTH_SHORT).show();
                }
                else if (!password.equals(confirmPassword)) {
                    // Passwords do not match, show an error message
                    Toast.makeText(signup.this, "Passwords do not match. Please try again.", Toast.LENGTH_SHORT).show();
                } else {
                    // Passwords match, check if the email already exists in the database
                    if (isEmailExists(email)) {
                        // Email already exists, show an error message
                        Toast.makeText(signup.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                    } else if (isPasswordExists(password)) {
                        // Password already exists, show an error message
                        Toast.makeText(signup.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                    } else {
                        // Email and password are valid and don't exist, proceed with database insertion
                        Log.d("User Input", "Email: " + email + ", Password: " + password);

                        SQLiteDatabase database = dbHelper.getWritableDatabase();

                        ContentValues values = new ContentValues();
                        values.put("email", email);
                        values.put("password", password);

                        long newRowId = database.insert("USERS", null, values);

                        if (newRowId != -1) {
                            // Insertion was successful
                            Intent intent = new Intent(signup.this, homepage.class);
                            startActivity(intent);
                        } else {
                            // Insertion failed
                            Toast.makeText(signup.this, "Sign up failed. Please try again.", Toast.LENGTH_SHORT).show();
                        }

                        dbHelper.close();
                    }
                }
            }
        });
    }

    private boolean isEmailExists(String email) {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM USERS WHERE email = ?";
        Cursor cursor = database.rawQuery(query, new String[]{email});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    private boolean isPasswordExists(String password) {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM USERS WHERE password = ?";
        Cursor cursor = database.rawQuery(query, new String[]{password});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }
}