package com.hilay.actionbar;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ColorPicker.OnFragmentInteractionListener {

    private static final int REQUEST_CODE_CALL = 1;
    int color;
    CoordinatorLayout clayout;

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_CALL) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Uri tel = Uri.parse("0523744456");
                Intent callIntent = new Intent(Intent.ACTION_CALL, tel);
                if (callIntent.resolveActivity(getPackageManager()) != null)
                    startActivity(callIntent);
                else
                    Toast.makeText(this, "No app found", Toast.LENGTH_SHORT).show();
            }
         else {
            Toast.makeText(this, "No Permission", Toast.LENGTH_SHORT).show();
        }
    }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        clayout = (CoordinatorLayout) findViewById(R.id.clayout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        String uri = ("tel:0523744456");

        switch (id) {
            case R.id.action_settings:
                return true;
            case R.id.action_call:
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{
                            Manifest.permission.CALL_PHONE
                    }, REQUEST_CODE_CALL);
                } else {
                    String tel = "0523744456";
                    Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(tel));
                    if ((callIntent.resolveActivity(getPackageManager())) != null) {
                        startActivity(callIntent);
                    } else {
                        Toast.makeText(this, "No app found", Toast.LENGTH_SHORT).show();
                    }
                }
                return true;
            case R.id.action_color:
                getSupportFragmentManager().beginTransaction().replace(R.id.blankFragment, new ColorPicker()).commit();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onFragmentInteraction(int color) {
        clayout.setBackgroundColor(color);

    }

    public void openDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.fragment_color_picker, clayout, false);
        builder.setView(view);
        builder.setCancelable(false);
        builder.show();
    }

}
