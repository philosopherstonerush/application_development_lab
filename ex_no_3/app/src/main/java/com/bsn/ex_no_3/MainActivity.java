package com.bsn.ex_no_3;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import com.bsn.ex_no_3.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        EditText num1 = (EditText) findViewById(R.id.editText1);
        EditText num2 = (EditText) findViewById(R.id.editText2);

        Button div = (Button) findViewById(R.id.button1);
        Button mul = (Button) findViewById(R.id.button2);
        Button add = (Button) findViewById(R.id.button3);
        Button sub = (Button) findViewById(R.id.button4);
        TextView submit = (TextView) findViewById(R.id.answer);

        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n1 = Integer.parseInt(String.valueOf(num1.getText()));
                int n2 = Integer.parseInt(String.valueOf(num2.getText()));

                submit.setText(String.valueOf(n1/n2));

            }
        });

        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int n1 = Integer.parseInt(String.valueOf(num1.getText()));
                int n2 = Integer.parseInt(String.valueOf(num2.getText()));

                submit.setText(String.valueOf(n1*n2));
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int n1 = Integer.parseInt(String.valueOf(num1.getText()));
                int n2 = Integer.parseInt(String.valueOf(num2.getText()));

                submit.setText(String.valueOf(n1+n2));
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int n1 = Integer.parseInt(String.valueOf(num1.getText()));
                int n2 = Integer.parseInt(String.valueOf(num2.getText()));

                submit.setText(String.valueOf(n1-n2));
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
}