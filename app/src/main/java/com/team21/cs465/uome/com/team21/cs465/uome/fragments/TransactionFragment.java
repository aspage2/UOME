package com.team21.cs465.uome.com.team21.cs465.uome.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.team21.cs465.uome.Favor;
import com.team21.cs465.uome.R;
import com.team21.cs465.uome.Transaction;


public class TransactionFragment extends Fragment {

    public TransactionFragment() {
        // Required empty public constructor
    }


    public static TransactionFragment newInstance(String uNameR, String uNameA, int points, String title) {
        TransactionFragment fragment = new TransactionFragment();
        Bundle args = new Bundle();
        args.putString("USER.REQUEST", uNameR);
        args.putString("USER.ACCEPT", uNameA);
        args.putInt ("FAVOR.POINTS", points);
        args.putString ("FAVOR.TITLE", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View ret = inflater.inflate(R.layout.fragment_transaction, container, false);
        Bundle args = getArguments();
        ((TextView)ret.findViewById(R.id.text_description)).setText(args.getString("USER.REQUEST")+" paid "
            +args.getString("USER.ACCEPT")+" "+args.getInt("FAVOR.POINTS")+ " points for:");
        ((TextView)ret.findViewById(R.id.news_title)).setText(args.getString("FAVOR.TITLE"));

        return ret;

    }
}
