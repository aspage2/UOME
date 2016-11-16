package com.team21.cs465.uome.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

        ListView view = (ListView)findViewById(R.id.list_user_names);
        Intent intent = getIntent();
        User user = Data.getUser(intent.getExtras().getString("USER.TAG"));
        ArrayList<String> items = new ArrayList<>();
        for (User u: user.getFriends())
            items.add(u.getfName()+" "+u.getlName());
        view.setAdapter (new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, items));
        view.setOnItemClickListener(this);
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
