package com.bsn.ex_no_7;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import com.bsn.ex_no_7.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private ActivityMainBinding binding;
    ImageView iv;
    Bitmap b;
    Canvas c;
    Paint p;
    float dx=0, dy=0, ux=0, uy=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = (ImageView) this.findViewById(R.id.image1);
        Display d = getWindowManager().getDefaultDisplay();
        float dw = d.getWidth();
        float dh = d.getHeight();

        b = Bitmap.createBitmap((int) dw, (int) dh, Bitmap.Config.ARGB_8888);
        c = new Canvas(b);
        p = new Paint();
        p.setColor(Color.BLUE);
        iv.setImageBitmap(b);
        iv.setOnTouchListener(this);

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

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                dx = event.getX();
                dy = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                ux = event.getX();
                uy = event.getY();
                c.drawLine(dx, dy, ux, uy, p);
                iv.invalidate();
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            default:
                break;
        }
        return true;

    }
}