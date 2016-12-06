package com.team21.cs465.uome;

public class Favor {

    private User requester;
    private int points;
    private String title;
    private String description;
    public Favor (User requester, int points, String favor)
    {
        this.requester = requester;
        this.points = points;
        this.title = favor;
        this.description = "<No Description>";
    }
    public Favor(User requester, int points, String favor, String description){
        this.requester = requester;
        this.points = points;
        this.title = favor;
        this.description = description;
    }


    public String getName (boolean full) {
        if (!full)
            return requester.getfName();
        else
            return requester.toString();
    }

    public int getPoints() {
        return points;
    }

    public String getTitle() {
        return title;
    }

    public User getRequester () {return requester;}

    public String getDescription () { return description; }



}
