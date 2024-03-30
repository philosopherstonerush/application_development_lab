package com.bsn.ex_no_2;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import com.bsn.ex_no_2.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    String name, dept, gender;
    float prog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final EditText e = (EditText) findViewById(R.id.editext1);
        RadioGroup rg=(RadioGroup)findViewById(R.id.radioGroup1);
        final RadioButton r1=(RadioButton)findViewById(R.id.radio0);
        final RadioButton r2=(RadioButton)findViewById(R.id.radio1);
        final Spinner s=(Spinner)findViewById(R.id.spinner1);
        RatingBar rb=(RatingBar)findViewById(R.id.ratingBar1);
        Button b=(Button)findViewById(R.id.button1);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.Gender,
                android.R.layout.simple_spinner_dropdown_item
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        s.setAdapter(adapter);

        rg.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener()
                {
                    @Override
                    public void onCheckedChanged(RadioGroup arg0, int arg1) {
                        if(r1.isChecked())
                            dept="B.E. CSE";
                        if(r2.isChecked())
                            dept="B.Tech IT";
                    }
                });

        rb.setOnRatingBarChangeListener(
                new RatingBar.OnRatingBarChangeListener()
                {
                    @Override
                    public void onRatingChanged(RatingBar arg0, float arg1,
                                                boolean arg2) {
// TODO Auto-generated method stub
                        prog=arg1;
                    }
                });
        b.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View arg0) {
                        name=e.getText().toString();
//                        gender= s.getSelectedItem().toString();
                        int degree_id = rg.getCheckedRadioButtonId();
                        RadioButton radioButton = (RadioButton) findViewById(degree_id);
                        String dept = (String) radioButton.getText();
                        Toast.makeText(MainActivity.this, "Name: " + name + "Gender: " + gender + "Degree" + dept , Toast.LENGTH_SHORT).show();
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