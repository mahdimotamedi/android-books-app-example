package com.azad.lib;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = new DbHelper(this);

        ConstraintLayout layout = new ConstraintLayout(MainActivity.this);
        layout.setBackgroundColor(Color.BLUE);

        setContentView(R.layout.activity_main);
    }


    public void onLogin(View view) {
        EditText usernameTxt = findViewById(R.id.usernameTxt);
        EditText passwordTxt = findViewById(R.id.passwordTxt);

        Cursor result = db.userGet(usernameTxt.getText().toString());

        if (result.getCount() <= 0) {
            Toast.makeText(this, "User do not exist.",
                    Toast.LENGTH_LONG).show();
            return;
        }

        StringBuffer data = new StringBuffer();
        while (result.moveToNext()) {
            if (passwordTxt.getText().toString().compareTo(result.getString(2)) < 0) {
                Toast.makeText(this, "Password is wrong.",
                        Toast.LENGTH_LONG).show();
                return;
            }
            else
                break;
        }

        Toast.makeText(this, "Login successful.",
                Toast.LENGTH_LONG).show();
        Intent myIntent = new Intent(this, BooksActivity.class);
        startActivity(myIntent);
    }
}
