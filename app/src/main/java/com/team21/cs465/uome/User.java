package com.team21.cs465.uome;

import java.util.ArrayList;

public class User {
    private String fName;
    private String lName;
    private String password;
    private ArrayList<Transaction> history;
    private ArrayList<Favor> favors;
    private ArrayList<Favor> myJobs;
    private ArrayList<User> friends;
    private String userTag;
    private int userProfileResource;
    private int level;
    private int progress;

    public User (String fName, String lName, String password, String userTag, int level, int progress, int profilePicResource)
    {
        this.userProfileResource = profilePicResource;

        this.fName = fName;
        this.lName = lName;
        this.password = password;
        this.userTag = userTag;
        this.level = level;
        this.progress = progress;
        this.history = new ArrayList<>();
        this.friends = new ArrayList<>();
        this.favors = new ArrayList<>();
        this.myJobs = new ArrayList<>();
    }

    public ArrayList<Favor> getMyJobs () { return myJobs; }

    public ArrayList<Favor> getFavors () { return favors; }
    public int getProgress() {
        return progress;
    }

    public ArrayList<Transaction> getHistory() {
        return history;
    }

    public void addNewTransactionToHistory (Favor f, User acceptor)
    {
        if (!favors.contains(f))
            return;
        favors.remove(f);
        history.add (new Transaction(f, acceptor));
    }


    public void createFavor (String title, int points, String desc)
    {
        favors.add(new Favor (this, points, title, desc));
    }


    public boolean acceptFavor(Favor t)
    {
        myJobs.add(t);

        progress += t.getPoints();
        if (progress >= 35)
        {
            level ++;
            progress = 35-progress+t.getPoints();
            return true;
        }
        return false;
    }
    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getPassword() {
        return password;
    }

    public String getUserTag() {
        return userTag;
    }

    public int getLevel () { return level; }

    public ArrayList<User> getFriends () { return friends; }

    public int getUserProfileResource() {
        return userProfileResource;
    }

    public String toString()
    {
        return fName + " " + lName;
    }
    public void addFriend (User other)
    {
        if (friends.contains(other))
            return;
        friends.add (other);
        other.getFriends().add(this);
    }
}
