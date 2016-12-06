package com.team21.cs465.uome.activities;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.team21.cs465.uome.NavigationBarActivity;
import com.team21.cs465.uome.Data;
import com.team21.cs465.uome.Favor;
import com.team21.cs465.uome.R;
import com.team21.cs465.uome.User;

import java.util.ArrayList;

public class MyFavors extends NavigationBarActivity {


    ListView favorList;
    ArrayList<Favor> myJobs;
    ArrayList<Favor> myRequests;
    ArrayList<Favor> data;
    myFavorsAdapter adapter;
    User me;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        me = Data.getUser(getIntent().getExtras().getString("USER.TAG"));

        setupNavigation (me, R.id.toolbar, R.layout.my_favors_view);
        favorList = (ListView) findViewById(R.id.myfavors_listview);
        myJobs = new ArrayList<>();
        myRequests = new ArrayList<>();
        fillRequestsData(myRequests);
        fillJobsData(myJobs);
        data = new ArrayList<>();
        data.addAll(myRequests);

        adapter = new myFavorsAdapter(this, data);
        favorList.setAdapter(adapter);

    }

    public void fillRequestsData(ArrayList<Favor> data){
        // Where we would normally fetch data from a database...
        if (me == null)return;
        for (Favor f : me.getFavors())
            data.add (f);

    }


    public void fillJobsData(ArrayList<Favor> data){
        // Where we would normally fetch data from a database...
        if (me == null)return;
        for (Favor f : me.getMyJobs())
            data.add (f);

    }

    public void filterClick(View v)
    {
        if (v.getId() == R.id.button_myJobs){
            Log.d(null, "changing data to jobs");
            data.clear();
            data.addAll(myJobs);
        }
        else if (v.getId() == R.id.button_myRequests){
            Log.d(null, "changing data to requests");
            data.clear();
            data.addAll(myRequests);
        }

        adapter.notifyDataSetChanged();

    }



    public class myFavorsAdapter extends BaseAdapter {
        private Context mContext;
        private LayoutInflater mInflater;
        private ArrayList<Favor> dataSource;

        public myFavorsAdapter(Context context, ArrayList<Favor> items) {
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
            favorRequester.setText(f.getName (false) + " is offering " + f.getPoints()+ " points for:");
            TextView favorTitle = (TextView)favorCell.findViewById(R.id.favor_title);
            favorTitle.setText(f.getTitle());

            final int index = i;
            Button dismissButton = (Button) favorCell.findViewById(R.id.favor_accept_button);
            dismissButton.setText("Dismiss");
            dismissButton.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Favor f = data.remove(index);
                            //me.acceptFavor(f);
                            //f.getRequester ().addNewTransactionToHistory  (f, me);
                            Toast.makeText(getApplicationContext(),"Favor Dismissed!",Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                        }
                    }
            );

            favorCell.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v){
                            // onClick listener when user clicks the cell
                        }
                    }
            );


            return favorCell;
        }
    }


}
