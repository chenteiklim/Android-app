package com.example.studenthelpdesk;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.ImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is signed in, which indicates Firebase is successfully connected.
        } else {
            // User is not signed in, which may indicate a connection issue or no user is signed in.
        }
        Button signUpButton = findViewById(R.id.signUpBtn);
        Button logInButton = findViewById(R.id.loginBtn);
        ImageView bookImg = findViewById(R.id.bookImg);

        // Set an OnClickListener for the "Sign Up" button
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to switch to the SignUpActivity
                Intent intent = new Intent(homepage.this, signup.class);
                startActivity(intent);
            }
        });
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to switch to the LoginActivity
                Intent logInIntent = new Intent(homepage.this, login.class); // Replace with your LoginActivity class
                startActivity(logInIntent);
            }
        });

        bookImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to switch to the LoginActivity
                Intent logInIntent = new Intent(homepage.this, book.class); // Replace with your LoginActivity class
                startActivity(logInIntent);
            }
        });
    }
}

