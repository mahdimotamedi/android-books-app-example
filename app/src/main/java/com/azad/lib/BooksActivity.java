package com.azad.lib;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class BooksActivity extends AppCompatActivity {

    DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = new DbHelper(this);

        ConstraintLayout layout = new ConstraintLayout(BooksActivity.this);
        layout.setBackgroundColor(Color.BLUE);

        setContentView(R.layout.activity_books);
    }


    public void saveClick(View view) {
        EditText idTxt = findViewById(R.id.idTxt);
        EditText titleTxt = findViewById(R.id.titleTxt);
        EditText publishDateTxt = findViewById(R.id.publishDateTxt);
        EditText writerTxt = findViewById(R.id.writerTxt);

        boolean res = db.bookInsert(idTxt.getText().toString(),
                titleTxt.getText().toString(),
                publishDateTxt.getText().toString(),
                writerTxt.getText().toString());

        if (res)
            Toast.makeText(this, "Done Successfully",
                    Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Error Inserting Data",
                    Toast.LENGTH_LONG).show();

    }

    public void delClick(View view) {
        EditText idTxt = findViewById(R.id.usernameTxt);
        //EditText nameTxt = findViewById(R.id.nameTxt);
        boolean res = db.bookDelete(idTxt.getText().toString());
        if (res)
            Toast.makeText(this, "Done Successfully",
                    Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Error Inserting Data",
                    Toast.LENGTH_LONG).show();

    }

    public void updateClick(View view) {
        EditText idTxt = findViewById(R.id.idTxt);
        EditText titleTxt = findViewById(R.id.titleTxt);
        EditText publishDateTxt = findViewById(R.id.publishDateTxt);
        EditText writerTxt = findViewById(R.id.writerTxt);

        boolean res = db.bookUpdate(idTxt.getText().toString(),
                titleTxt.getText().toString(),
                publishDateTxt.getText().toString(),
                writerTxt.getText().toString());

        if (res)
            Toast.makeText(this, "Update Successfully",
                    Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Error Updating Data",
                    Toast.LENGTH_LONG).show();

    }

    public void onShowClick(View view) {
        Cursor result = db.bookGetAll();
        if (result.getCount() <= 0) {
            Toast.makeText(this, "No Data Exists",
                    Toast.LENGTH_LONG).show();
            return;
        }
        StringBuffer data = new StringBuffer();
        while (result.moveToNext()) {
            data.append("ID: ").append(result.getString(0)).append("\n");
            data.append("title: ").append(result.getString(1)).append("\n");
            data.append("publish date: ").append(result.getString(2)).append("\n");
            data.append("writer: ").append(result.getString(3)).append("\n\n");
        }
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setCancelable(true);
        alert.setTitle("All Data");
        alert.setMessage(data.toString());
        alert.show();
    }
}
