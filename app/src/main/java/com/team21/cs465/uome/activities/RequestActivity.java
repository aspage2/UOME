package com.team21.cs465.uome.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.team21.cs465.uome.NavigationBarActivity;
import com.team21.cs465.uome.Data;
import com.team21.cs465.uome.R;
import com.team21.cs465.uome.User;

public class RequestActivity extends NavigationBarActivity implements View.OnClickListener{
    private TextView titleText, pointsText, descText;
    private User me;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        me = Data.getUser(getIntent().getExtras().getString("USER.TAG"));

        titleText = (EditText)findViewById(R.id.text_title);
        pointsText = (EditText)findViewById(R.id.text_points);
        descText = (EditText)findViewById(R.id.text_description);

        findViewById(R.id.button_create_favor).setOnClickListener(this);
    }

    @Override
    public void onClick (View v)
    {
        me.createFavor(titleText.getText().toString(), Integer.parseInt (pointsText.getText().toString()), descText.getText().toString());
        Intent i = new Intent (this, MyFavors.class);
        i.putExtra("USER.TAG", me.getUserTag());
        startActivity (i);
    }
}
