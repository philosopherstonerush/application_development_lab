package com.bsn.ex_no_9;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        final TextView textView = (TextView) findViewById(R.id.text_one);

        Button b = (Button) findViewById(R.id.button1);

        b.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Runnable r = new Runnable() {
                            @Override
                            public void run() {
                                for(int i = 0; i < 100; i++) {
                                    final int temp = i;
                                    try {
                                        Thread.sleep(2000);
                                    }catch (Exception e) {
                                        e.printStackTrace();;
                                    }
                                    progressBar.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            progressBar.setProgress(temp);
                                            textView.setText(String.valueOf(temp) + '%');
                                        }
                                    });
                                }
                            }
                        };

                        new Thread(r).start();

                    }
                }
        );

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