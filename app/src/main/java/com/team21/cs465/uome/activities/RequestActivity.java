package com.team21.cs465.uome.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.team21.cs465.uome.CustomActionBarActivity;
import com.team21.cs465.uome.Data;
import com.team21.cs465.uome.R;
import com.team21.cs465.uome.User;

public class RequestActivity extends CustomActionBarActivity {
    private TextView titleText, pointsText, descText;
    private Button createButton;
    private User me;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        setupActionBar("Create a Favor", false);

        me = Data.getUser(getIntent().getExtras().getString("USER.TAG"));

        findViewById(R.id.button_create_favor).setOnClickListener(this);
        titleText = (EditText)findViewById(R.id.text_title);
        pointsText = (EditText)findViewById(R.id.text_points);
        descText = (EditText)findViewById(R.id.text_description);
    }

    @Override
    public void onClick (View v)
    {
        switch (v.getId()){
            case R.id.button_create_favor:
                me.createFavor(titleText.getText().toString(), Integer.parseInt(pointsText.getText().toString()));
                Toast.makeText(this, "Favor Submitted", Toast.LENGTH_SHORT).show();
                break;
            default:
                super.onClick(v);
        }
    }
}
