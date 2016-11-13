package com.team21.cs465.uome.activities;

/**
 * Created by Collin on 11/13/16.
 */

public class News extends Object {
    public String requester;
    public String receiver;
    public int points;
    public String favor;

    public News(String requester, String receiver, int points, String favor){
        this.requester = requester;
        this.receiver = receiver;
        this.points = points;
        this.favor = favor;
    }

}
