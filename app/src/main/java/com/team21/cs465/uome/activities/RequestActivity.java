package com.team21.cs465.uome.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.team21.cs465.uome.CustomActionBarActivity;
import com.team21.cs465.uome.R;

public class RequestActivity extends CustomActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        setupActionBar("Create a Favor", false);

        findViewById(R.id.button_create_favor).setOnClickListener(this);
    }

    @Override
    public void onClick (View v)
    {
        switch (v.getId()){
            case R.id.button_create_favor:
                Toast.makeText(this, "Favor Submitted", Toast.LENGTH_SHORT).show();
                break;
            default:
                super.onClick(v);
        }
    }
}
