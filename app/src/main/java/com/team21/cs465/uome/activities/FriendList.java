package com.team21.cs465.uome.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

import com.team21.cs465.uome.CustomActionBarActivity;
import com.team21.cs465.uome.Data;
import com.team21.cs465.uome.R;
import com.team21.cs465.uome.User;

import java.util.ArrayList;

public class FriendList extends CustomActionBarActivity implements AdapterView.OnItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);
        setupActionBar("Friend List", true);


        Intent intent = getIntent();
        User user = Data.getUser(intent.getExtras().getString("USER.TAG"));

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.friend_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new FriendListAdapter(user.getFriends()));
    }

    @Override
    public void onItemClick (AdapterView<?> adapterView, View v, int position, long id)
    {
        User user = Data.getUser(getIntent().getExtras().getString("USER.TAG"));
        Intent intent = new Intent (this, UserProfile.class);
        intent.putExtra("USER.TAG", user.getFriends().get(position).getUserTag());
        intent.putExtra("USER.ISME", false);
        startActivity (intent);
    }
}
