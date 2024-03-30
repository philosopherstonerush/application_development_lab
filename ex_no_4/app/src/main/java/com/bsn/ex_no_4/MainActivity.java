package com.bsn.ex_no_4;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.app.AlertDialog.Builder;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import com.bsn.ex_no_4.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    EditText name, regno, mark;
    Button view, viewAll, add, update, delete;
    SQLiteDatabase db;
    StudentSqliteHelpher openHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        regno = (EditText) findViewById(R.id.editText1);
        name = (EditText) findViewById(R.id.editText2);
        mark = (EditText) findViewById(R.id.editText3);
        add = (Button) findViewById(R.id.add);
        view = (Button) findViewById(R.id.view);
        viewAll = (Button) findViewById(R.id.viewAll);
        update = (Button) findViewById(R.id.update);
        delete = (Button) findViewById(R.id.delete);


        openHelper = new StudentSqliteHelpher(getApplicationContext());
        db = openHelper.getWritableDatabase();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String reg_no = regno.getText().toString();
                String name_of_student = name.getText().toString();
                String mark_of_student = mark.getText().toString();

                db.execSQL("INSERT INTO student VALUES('"+reg_no+"','"+name_of_student+"','"+mark_of_student+"');");
                showMessage("Success", "Record added");

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String reg_no = regno.getText().toString();

                db.execSQL("DELETE FROM student WHERE regno='"+reg_no+"'");
                showMessage("Success", "Record Deleted");

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String reg_no = regno.getText().toString();
                String name_of_student = name.getText().toString();
                String mark_of_student = mark.getText().toString();

                db.execSQL("UPDATE student SET name='"+name_of_student+"',mark='"+mark_of_student+"' WHERE regno='"+reg_no+"'");
                showMessage("Success", "Record updated");
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String reg_no = regno.getText().toString();
                Cursor c =db.rawQuery("SELECT * FROM student WHERE regno='"+reg_no+"'", null);
                c.moveToFirst();
                name.setText(c.getString(1));
                mark.setText(c.getString(2));

            }
        });

        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c=db.rawQuery("SELECT * FROM student", null);
                StringBuilder sb = new StringBuilder();

                while(c.moveToNext())
                {
                    sb.append("Reg. No : "+c.getString(0)+"\n");
                    sb.append("Name : "+c.getString(1)+"\n");
                    sb.append("Mark : "+c.getString(2)+"\n\n");
                }

                showMessage("student details", sb.toString());

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showMessage(String title,String message)
    {
        Builder builder=new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void clearText()
    {
        regno.setText("");
        name.setText("");
        mark.setText("");
        regno.requestFocus();
    }


}