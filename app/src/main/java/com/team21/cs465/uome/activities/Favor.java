package com.team21.cs465.uome.activities;

/**
 * Created by Collin on 11/13/16.
 */

public class Favor extends Object {
    public String requester;
    public int points;
    public String favor;

    public Favor(String requester, int points, String favor){
        this.requester = requester;
        this.points = points;
        this.favor = favor;
    }

}
