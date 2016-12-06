package com.team21.cs465.uome;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NavUtils;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.team21.cs465.uome.activities.FavorFeed;
import com.team21.cs465.uome.activities.FriendList;
import com.team21.cs465.uome.activities.MyFavors;
import com.team21.cs465.uome.activities.NewsFeed;
import com.team21.cs465.uome.activities.RequestActivity;
import com.team21.cs465.uome.activities.UserProfile;

import de.hdodenhof.circleimageview.CircleImageView;

public abstract class NavigationBarActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private User me;
    private DrawerLayout drawerLayout;
    protected void setupNavigation (User me, int toolbarResource, int contentResource) {
        this.me = me;
        setContentView (R.layout.layout_navigation_drawer);

        LayoutInflater.from(this).inflate(contentResource, (FrameLayout)findViewById(R.id.container_main_content),true);

        Toolbar toolbar = (Toolbar)findViewById(toolbarResource);
        toolbar.setTitleTextColor(0xffffffff);
        toolbar.setBackgroundColor(0xff182d9e);
        setSupportActionBar(toolbar);

        NavigationView navigationView = (NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        View v = navigationView.getHeaderView(0);
        CircleImageView img = (CircleImageView)v.findViewById(R.id.header_image_view);
        TextView txt = (TextView)v.findViewById(R.id.header_name_text);
        txt.setText(me.getfName()+" "+me.getlName().charAt(0)+".");
        new BitmapLoader(img, getResources()).execute(me.getUserProfileResource(), 50, 50);

        drawerLayout = (DrawerLayout)findViewById(R.id.navigation_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Class<?> cls;

        switch (item.getItemId())
        {
            case R.id.action_my_profile:
                cls = UserProfile.class;
                break;
            case R.id.action_friend_list:
                cls = FriendList.class;
                break;
            case R.id.action_transactions:
                cls = MyFavors.class;
                break;
            case R.id.action_favor_feed:
                cls = FavorFeed.class;
                break;
            case R.id.action_news_feed:
                cls = NewsFeed.class;
                break;
            case R.id.action_request:
                cls = RequestActivity.class;
                break;
            case R.id.action_logout:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return false;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        Intent i = new Intent (this, cls);
        i.putExtra ("USER.TAG", me.getUserTag());
        i.putExtra ("USER.ISME", true);
        startActivity (i);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_request,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item)
    {
        if (item.getItemId() == R.id.action_request)
        {
            Intent i = new Intent (this, RequestActivity.class);
            i.putExtra("USER.TAG", me.getUserTag());
            startActivity (i);
            return true;
        }
        return super.onOptionsItemSelected (item);
    }
}
