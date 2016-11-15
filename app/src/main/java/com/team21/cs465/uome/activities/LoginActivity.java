package com.team21.cs465.uome.activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.team21.cs465.uome.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            bar.setCustomView(R.layout.layout_login_actionbar);
        }

        findViewById(R.id.button_sign_in).setOnClickListener(this);
    }

    @Override
    public void onClick (View v)
    {
        switch (v.getId())
        {
            case R.id.button_sign_in:
                startActivity (new Intent (this, NavigationActivity.class));
        }
    }
}
