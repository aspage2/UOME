package com.team21.cs465.uome;

import android.support.v4.app.Fragment;

import com.team21.cs465.uome.com.team21.cs465.uome.fragments.TransactionFragment;

public class Transaction {
    private Favor favor;

    public Favor getFavor() {
        return favor;
    }

    public User getAcceptor() {
        return acceptor;
    }

    private User acceptor;

    public Transaction(Favor favor, User acceptor) {
        this.favor = favor;
        this.acceptor = acceptor;
    }

    public TransactionFragment getFragmentRepresentation ()
    {
        return TransactionFragment.newInstance(favor.getName(false), acceptor.getfName(), favor.getPoints(), favor.getTitle());
    }
}
