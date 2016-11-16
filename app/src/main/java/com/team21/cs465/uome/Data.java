package com.team21.cs465.uome;

import java.util.ArrayList;

public class Data {

    private static ArrayList<User> users = null;
    public static User COLLIN, ROHIT, RAVID, ALEX, ANANYA;

    private static void initUsers ()
    {
        users = new ArrayList<>();
        users.add (new User ("Rohit", "Saigal", "1234", "rohitsaigal95", 20, 16, R.drawable.profile_rohit));
        ROHIT = users.get(0);
        users.add (new User ("Collin", "Walther", "1234", "cwalthe2", 26, 12, R.drawable.profile_collin));
        COLLIN = users.get(1);
        users.add (new User ("Ravid", "Chi", "1234", "ravidchi", 3, 10, R.drawable.profile_ravid));
        RAVID = users.get(2);
        users.add (new User ("Ananya", "Cleetus", "1234", "ananyacleetus", 12, 1, R.drawable.profile_ananya));
        ANANYA = users.get (3);
        users.add (new User ("Alex", "Page", "1234", "aspage2", 1, 34, R.drawable.profile_alex));
        ALEX = users.get (4);

        ROHIT.addFriend(COLLIN);
        ROHIT.addFriend(RAVID);
        ROHIT.addFriend(ANANYA);
        ROHIT.addFriend(ALEX);
        ROHIT.getHistory().add(new Transaction(new Favor (ROHIT, 3, "Take notes for me"), COLLIN));
        ROHIT.getHistory().add (new Transaction(new Favor (ROHIT, 2, "Walk my dog"), RAVID));
        ROHIT.getHistory().add (new Transaction (new Favor(ALEX, 3, "Pick up my bags"), ROHIT));

        COLLIN.addFriend (RAVID);
        COLLIN.addFriend (ALEX);
        COLLIN.getHistory().add (new Transaction(new Favor (COLLIN, 5, "Give me a ride"), ALEX));
        COLLIN.getFavors().add (new Favor (COLLIN, 3, "Borrow a pencil"));
        COLLIN.getFavors().add (new Favor (COLLIN, 6, "Give me a ride"));
        RAVID.addFriend (ANANYA);
        RAVID.getHistory().add (new Transaction(new Favor (RAVID, 4, "Hook up my stereo system"), ANANYA));


        ANANYA.addFriend(ALEX);
        ANANYA.getFavors().add (new Favor (ANANYA, 1, "Find my wallet"));
    }

    public static String describe (int level)
    {
        if (level > 20)
            return "Favor Junkie";
        if (level > 15)
            return "Favor Master";
        if (level > 10)
            return "Favor Fever";
        if (level > 5)
            return "Favor Lover";
        else
            return "Favor Newbie";
    }
    public static void addUser (User u)
    {
        if (users == null)
            initUsers();
        users.add (u);
    }
    public static User getUser (String userTag)
    {
        if (users == null)
            initUsers ();
        for (User u : users)
            if (u.getUserTag().equals(userTag))
                return u;
        return null;
    }
}
