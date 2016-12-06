package com.team21.cs465.uome;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.team21.cs465.uome.activities.UserProfile;

import java.util.ArrayList;

public class FriendListAdapter extends RecyclerView.Adapter<FriendListAdapter.ViewHolder> {
    private ArrayList<User> friends;
    private Context context;
    public FriendListAdapter (ArrayList<User> friends)
    {
        this.friends = friends;
    }

    @Override
    public void onBindViewHolder (ViewHolder holder, int position)
    {
        User friend = friends.get(position);
        holder.nameText.setText(friend.toString());
        holder.userTag = friend.getUserTag();
    }

    @Override
    public FriendListAdapter.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType)
    {
        return new ViewHolder(LayoutInflater.from (parent.getContext()).inflate(R.layout.layout_friend_entry, parent, false));
    }

    @Override
    public int getItemCount ()
    {
        return friends.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nameText;
        public String userTag;
        public ViewHolder (View v)
        {
            super(v);
            nameText = (TextView)v.findViewById(R.id.text_friend_name);
            nameText.setOnClickListener (this);
        }

        @Override
        public void onClick (View v)
        {
            if (v.getId() == R.id.text_friend_name)
            {
                Intent intent = new Intent (v.getContext(), UserProfile.class);
                intent.putExtra("USER.TAG", userTag);
                intent.putExtra("USER.ISME", false);
                v.getContext().startActivity (intent);
            }
        }
    }
}
