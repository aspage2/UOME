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


public class TransactionFragment extends Fragment implements View.OnClickListener {

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


        ret.findViewById(R.id.news_like_button).setOnClickListener(this);
        //ret.findViewById(R.id.news_comment_button).setOnClickListener(this);
        return ret;

    }

    @Override
    public void onClick (View v)
    {
        switch (v.getId())
        {
            case R.id.news_like_button:
                likeClicked(v);
        }
    }

    public void likeClicked(View v){
        LinearLayout c = (LinearLayout)v.getParent();
        Button like_label = (Button)c.findViewById(R.id.news_like_name);

        if (v.getBackground().getConstantState()==getResources().getDrawable(R.drawable.like_icon).getConstantState()){
            v.setBackgroundResource(R.drawable.like_icon_blue);
            like_label.setText("Collin W."); //TODO - replace Collin W. with actual user's name
        }
        else{
            v.setBackground(getResources().getDrawable(R.drawable.like_icon));
            like_label.setText("");
        }
    }
}
