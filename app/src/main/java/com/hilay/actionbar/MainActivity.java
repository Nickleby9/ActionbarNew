package com.hilay.actionbar;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
        int id = item.getItemId();
        String uri = ("tel:0523744456");

        switch (id) {
            case R.id.action_settings:
                return true;
            case R.id.action_call:
                int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
                if (permission == PackageManager.PERMISSION_GRANTED) {
                    Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(uri));
                    if (callIntent.resolveActivity(getPackageManager()) != null) {
                            startActivity(callIntent);
                    } else {
                        Toast.makeText(this, "No suitable app found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                    try {
                        Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(uri));
                    if (callIntent.resolveActivity(getPackageManager()) != null) {
                            startActivity(callIntent);
                    } else {
                        Toast.makeText(this, "No suitable app found", Toast.LENGTH_SHORT).show();
                    } catch {
                        Toast.makeText(this, "No permissions", Toast.LENGTH_SHORT).show();
                    }
                    }
                }
                return true;
            case R.id.action_dial:
                Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse(uri));
                if (dialIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(dialIntent);
                }
                return true;

        }


        return super.onOptionsItemSelected(item);
    }
}
