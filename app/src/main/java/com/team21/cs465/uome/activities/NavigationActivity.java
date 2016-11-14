package com.team21.cs465.uome.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.team21.cs465.uome.R;

public class NavigationActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);


    }

    @Override
    public void onClick (View v)
    {
        switch (v.getId())
        {
            case R.id.button_my_profile:

                break;
            case R.id.button_friend_list:
//                startActivity (new Intent )
                break;
            case R.id.button_favor_feed:
                startActivity (new Intent(this, FavorFeed.class));
                break;
            case R.id.button_news_feed:
                startActivity (new Intent (this, NewsFeed.class));
                break;
        }
    }
}
