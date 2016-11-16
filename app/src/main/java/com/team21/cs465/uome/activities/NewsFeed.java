package com.team21.cs465.uome.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.team21.cs465.uome.CustomActionBarActivity;
import com.team21.cs465.uome.Data;
import com.team21.cs465.uome.Favor;
import com.team21.cs465.uome.R;
import com.team21.cs465.uome.Transaction;
import com.team21.cs465.uome.User;

import java.util.ArrayList;

import static com.team21.cs465.uome.Data.*;
/**
 * Created by Collin on 11/12/16.
 *
 * The Favor Feed controller. Displays a list of favors that a user can choose to accept
 */

public class NewsFeed extends CustomActionBarActivity
{
    User me;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_feed_view);

        me = Data.getUser(getIntent().getExtras().getString("USER.TAG"));
        ListView favorList = (ListView) findViewById(R.id.news_table);
        ArrayList<Transaction> newsData = new ArrayList<Transaction>();
        fillNewsData(newsData);
        favorfeedAdapter adapter = new favorfeedAdapter(this, newsData);
        favorList.setAdapter(adapter);

        setupActionBar("Transaction Feed", true);
    }

    public void fillNewsData(ArrayList<Transaction> data){
        // Where we would normally fetch data from a database...
        for (User u : me.getFriends())
            for (Transaction t : u.getHistory())
                data.add (t);

    }


    public class favorfeedAdapter extends BaseAdapter{
        private Context mContext;
        private LayoutInflater mInflater;
        private ArrayList<Transaction> dataSource;

        public favorfeedAdapter(Context context, ArrayList<Transaction> items) {
            mContext = context;
            dataSource = items;
            mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }


        @Override
        public int getCount() {
            return dataSource.size();
        }

        @Override
        public Object getItem(int position) {
            return dataSource.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int i, View view, ViewGroup parent) {
            LinearLayout newsCell = (LinearLayout) mInflater.inflate(R.layout.news_table_cell, parent, false);
            Transaction n = dataSource.get(i);

            TextView newsRequester = (TextView)newsCell.findViewById(R.id.news_requester);
            newsRequester.setText(n.getFavor().getName(false) + " awarded " + n.getAcceptor().getfName() + " " + n.getFavor().getPoints() + " points for:");
            TextView favorTitle = (TextView)newsCell.findViewById(R.id.news_title);
            favorTitle.setText(n.getFavor().getTitle());

            return newsCell;
        }
    }


    public void favorClicked(View v){

        System.out.println("Favor clicked");
    }

    public void likeClicked(View v){
        LinearLayout c = (LinearLayout)v.getParent();
        Button like_label = (Button)c.findViewById(R.id.news_like_name);

        if (v.getBackground().getConstantState()==getResources().getDrawable(R.drawable.like_icon).getConstantState()){
            v.setBackgroundResource(R.drawable.like_icon_blue);
            like_label.setText(me.getfName()); //TODO - replace Collin W. with actual user's name
        }
        else{
            v.setBackground(getResources().getDrawable(R.drawable.like_icon));
            like_label.setText("");
        }
    }

    public void likedNamesClicked(View v){
        // TODO - show a list of who liked the post
    }

    public void commentClicked(View v){
        // TODO - show separate view for users to leave/view comments
    }


}
