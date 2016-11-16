package com.team21.cs465.uome;

public class Favor {

    public User getRequester() {
        return requester;
    }

    public int getPoints() {
        return points;
    }

    public String getFavorTitle() {
        return favorTitle;
    }

    private User requester;
    private int points;
    private String favorTitle;
    private int idNum;

    public Favor(User requester, int points, String favor){
        this.requester = requester;
        this.points = points;
        this.favorTitle = favor;
    }


}
