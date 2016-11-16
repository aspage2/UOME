package com.team21.cs465.uome.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.team21.cs465.uome.CustomActionBarActivity;
import com.team21.cs465.uome.Data;
import com.team21.cs465.uome.Favor;
import com.team21.cs465.uome.R;

import java.util.ArrayList;

/**
 * Created by Collin on 11/12/16.
 *
 * The Favor Feed controller. Displays a list of favors that a user can choose to accept
 */

public class FavorFeed extends CustomActionBarActivity
{
    ListView favorList;
    ArrayList<Favor> favorData;
    favorfeedAdapter adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favor_feed_view);

        favorList = (ListView) findViewById(R.id.favor_table);
        favorData = new ArrayList<>();
        fillFavorData(favorData);
        favorfeedAdapter adapter = new favorfeedAdapter(this, favorData);
        favorList.setAdapter(adapter);

        setupActionBar("Favor Feed", true);
    }


    public void fillFavorData(ArrayList<Favor> data){
        // Where we would normally fetch data from a database...

        Favor f1 = new Favor(Data.getUser("rohitsaigal95"), 4, "Getting Pizza");
        Favor f2 = new Favor(Data.getUser("cwalthe2"), 1, "Lending a pencil");
        Favor f3 = new Favor(Data.getUser("ravidchi"), 6, "Doing my math assignment");
        Favor f4 = new Favor(Data.getUser("ravidchi"), 3, "Driving me to work");

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

            TextView favorRequester = (TextView)favorCell.findViewById(R.id.favor_requester);
            favorRequester.setText(f.getRequester().getfName() + " is offering " + f.getPoints()+ " points for:");
            TextView favorTitle = (TextView)favorCell.findViewById(R.id.favor_title);
            favorTitle.setText(f.getFavorTitle());

            final int index = i;
            Button acceptButton = (Button) favorCell.findViewById(R.id.favor_accept_button);
            acceptButton.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            favorData.remove(index);
                            notifyDataSetChanged();
                        }
                    }
            );

            favorCell.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v){
                            showDetails(index);
                        }
                    }
            );


            return favorCell;
        }
    }


    public void showDetails(int index){
        Favor fav = favorData.get(index);

        FrameLayout backView = (FrameLayout) findViewById(R.id.favor_detail_backview);
        TextView name = (TextView) findViewById(R.id.favor_detail_name);
        TextView title = (TextView) findViewById(R.id.favor_detail_title);
        TextView points = (TextView) findViewById(R.id.favor_detail_points);
        TextView description = (TextView) findViewById(R.id.favor_detail_description);

        String nameText = "<b>" + "Name: " + "</b> " + fav.getRequester();
        String favorText = "<b>" + "Favor: " + "</b> " + fav.getFavorTitle();
        String pointText = "<b>" + "Points: " + "</b> " + fav.getPoints();
        String descriptionText = "<b>" + "Description: " + "</b> " + "Example description";
        name.setText(Html.fromHtml(nameText));
        title.setText(Html.fromHtml(favorText));
        points.setText(Html.fromHtml(pointText));
        description.setText(Html.fromHtml(descriptionText));

        backView.setVisibility(View.VISIBLE);
    }
    public void hideDetails(View v){
        FrameLayout backView = (FrameLayout) findViewById(R.id.favor_detail_backview);
        backView.setVisibility(View.GONE);
    }

    public void doNothing(View v){
        // The onClick function for the favor-details linear layout.
        // Without this function, the user can click on the details box
        // to hide the details view which is undesired.
        // The user should only be allowed to click the black background
        // to hide the details view.
    }


}
