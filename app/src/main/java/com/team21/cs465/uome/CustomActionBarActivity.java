package com.team21.cs465.uome;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public abstract class CustomActionBarActivity extends AppCompatActivity implements View.OnClickListener {

    protected void setupActionBar (String title, boolean enableUpButton) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        View customView = getLayoutInflater().inflate(R.layout.layout_action_bar, null);
        TextView titleview = (TextView)customView.findViewById(R.id.action_bar_title);
        titleview.setText(title);

        Button button = (Button) customView.findViewById(R.id.nav_button);
        if (!enableUpButton)
            button.setVisibility(View.INVISIBLE);
        else
            button.setOnClickListener(this);
        customView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));
        actionBar.setCustomView(customView);
        Toolbar parent =(Toolbar) customView.getParent();
        parent.setPadding(0,0,0,0);
        parent.setContentInsetsAbsolute(0,0);
    }

    @Override
    public void onClick (View v)
    {
        switch (v.getId())
        {
            case R.id.nav_button:
                Toast.makeText(this, "Nav Button Pressed", Toast.LENGTH_SHORT).show();
        }
    }
}
