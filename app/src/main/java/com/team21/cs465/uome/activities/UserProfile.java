package com.team21.cs465.uome.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.team21.cs465.uome.Data;
import com.team21.cs465.uome.NavigationBarActivity;
import com.team21.cs465.uome.R;
import com.team21.cs465.uome.Transaction;
import com.team21.cs465.uome.User;

import com.team21.cs465.uome.BitmapLoader;

public class UserProfile extends NavigationBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        User u = Data.getUser(intent.getExtras().getString("USER.TAG"));
        boolean isAppUser = intent.getExtras().getBoolean("USER.ISME");
        if (u == null)
            return;
        //Toolbar toolbar = (Toolbar)findViewById();
        setupNavigation (u, R.id.profile_toolbar, R.layout.layout_user_profile);

        ((TextView)findViewById(R.id.fullName)).setText(u.getfName()+" "+u.getlName());
        ((TextView)findViewById(R.id.userName)).setText(u.getUserTag());
        ((TextView)findViewById(R.id.levelNum)).setText(Integer.toString(u.getLevel()));
        ((TextView)findViewById(R.id.text_level_desc)).setText(Data.describe (u.getLevel()));
        if (isAppUser)
            ((ProgressBar)findViewById(R.id.progress_user_level)).setProgress (u.getProgress());
        else
            findViewById(R.id.progress_user_level).setVisibility(View.INVISIBLE);
        new BitmapLoader((ImageView)findViewById(R.id.image_user_profile), getResources()).execute(u.getUserProfileResource());
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        for (Transaction t : u.getHistory())
            transaction.add (R.id.layout_user_activity,t.getFragmentRepresentation());
        transaction.commit();
    }

}
