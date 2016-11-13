package com.team21.cs465.uome.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TableLayout.LayoutParams;

import com.team21.cs465.uome.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Collin on 11/12/16.
 *
 * The Favor Feed controller. Displays a list of favors that a user can choose to accept
 */

public class FavorFeed extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favor_feed_view);

        /*
        TableLayout favorTable = (TableLayout) findViewById(R.id.favor_table);
        LinearLayout favorCell = (LinearLayout) getLayoutInflater().inflate(R.layout.favor_table_cell, null);
        TextView favorRequester = (TextView)favorCell.findViewById(R.id.favor_requester);
        favorRequester.setText("Test Person");
        TextView favorTitle = (TextView)favorCell.findViewById(R.id.favor_title);
        favorRequester.setText("Test Favor");



        favorTable.addView(favorCell);
        LinearLayout favorCell2 = (LinearLayout) getLayoutInflater().inflate(R.layout.favor_table_cell, null);
        favorTable.addView(favorCell2);
        */

        ListView favorList = (ListView) findViewById(R.id.favor_table);
        ArrayList<Favor> favorData = new ArrayList<Favor>();
        fillFavorData(favorData);
        favorfeedAdapter adapter = new favorfeedAdapter(this, favorData);
        favorList.setAdapter(adapter);

    }

    public void fillFavorData(ArrayList<Favor> data){
        // Where we would normally fetch data from a database...

        Favor f1 = new Favor("Rohit S.", 4, "Driving me to class");
        Favor f2 = new Favor("Alex P.", 1, "Lending a pencil");
        Favor f3 = new Favor("Ananya C.", 6, "Doing my math assignment");
        Favor f4 = new Favor("Ravid C.", 3, "Driving me to work");

        data.add(f1);
        data.add(f2);
        data.add(f3);
        data.add(f4);
    }


    public class favorfeedAdapter extends BaseAdapter{
        private Context mContext;
        private LayoutInflater mInflater;
        private ArrayList<Favor> dataSource;

        public favorfeedAdapter(Context context, ArrayList<Favor> items) {
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
            LinearLayout favorCell = (LinearLayout) mInflater.inflate(R.layout.favor_table_cell, parent, false);
            Favor f = dataSource.get(i);
            System.out.println(f.favor);

            TextView favorRequester = (TextView)favorCell.findViewById(R.id.favor_requester);
            favorRequester.setText(f.requester + " is awarding " + f.points + " points for:");
            TextView favorTitle = (TextView)favorCell.findViewById(R.id.favor_title);
            favorTitle.setText(f.favor);

            return favorCell;
        }
    }


    public void favorClicked(View v){
        System.out.println("Favor clicked");
    }


}
