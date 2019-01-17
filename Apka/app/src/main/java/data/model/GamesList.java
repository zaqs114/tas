package data.model;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GamesList {

    private ArrayList<Games> gamesList;

    public ArrayList<Games> getGamesArrayList() {

        return gamesList;
    }

    public void setGamesArrayList(ArrayList<Games> gamesArrayList) {
        this.gamesList = gamesArrayList;
    }
}