package com.team21.cs465.uome.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.team21.cs465.uome.CustomActionBarActivity;
import com.team21.cs465.uome.Data;
import com.team21.cs465.uome.R;
import com.team21.cs465.uome.User;

public class NavigationActivity extends CustomActionBarActivity implements View.OnClickListener {

    private User me;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        for (int id : new int[]{R.id.button_favor_feed, R.id.button_my_profile, R.id.button_friend_list, R.id.button_news_feed, R.id.button_create_favor, R.id.button_my_favors})
            findViewById(id).setOnClickListener(this);

        setupActionBar("Welcome", false);

        Intent intent = getIntent();
        me = Data.getUser(intent.getExtras().getString("USER.TAG"));
    }

    @Override
    public void onClick (View v)
    {
        Intent intent;
        switch (v.getId())
        {
            case R.id.button_my_profile:
                intent = new Intent (this, UserProfile.class);
                intent.putExtra ("USER.TAG", me.getUserTag());
                intent.putExtra ("USER.ISME", true);
                startActivity (intent);
                break;
            case R.id.button_friend_list:
                intent = new Intent (this, FriendList.class);
                intent.putExtra ("USER.TAG", me.getUserTag());
                startActivity (intent);
                break;
            case R.id.button_favor_feed:
                intent = new Intent (this, FavorFeed.class);
                intent.putExtra ("USER.TAG", me.getUserTag());
                startActivity (intent);
                break;
            case R.id.button_news_feed:
                intent = new Intent (this, NewsFeed.class);
                intent.putExtra ("USER.TAG", me.getUserTag());
                startActivity (intent);
                break;
            case R.id.button_create_favor:
                intent = new Intent (this, RequestActivity.class);
                intent.putExtra ("USER.TAG", me.getUserTag());
                startActivity (intent);
                break;
            case R.id.button_my_favors:
                Log.d(null, "onClick: ");
                intent = new Intent (this, MyFavors.class);
                intent.putExtra ("USER.TAG", me.getUserTag());
                startActivity (intent);
                break;
            default:
                super.onClick(v);
        }
    }
}
