package com.webapp.tas.controllers;

import com.webapp.tas.objects.Game;
import com.webapp.tas.tables.records.GamesRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.webapp.tas.Tables.GAMES;

import java.util.List;

@RestController
public class GameController {

    @Autowired
    private DSLContext jooq;

    @GetMapping("/games")
    public List<Game> gamesList(){
        return jooq.select().from(GAMES).fetchInto(Game.class);
    }

    /**
     * Method of validating admin permissions needs to be added
     * GameId validation method needs to be added
     * launch_date format needs to be tested (java.sql vs java.utl?)
     * test commit from mac
     * @param newGame
     */
    @PostMapping("/addgame")
    public void addGame(@RequestBody Game newGame){
        GamesRecord game = jooq.newRecord(GAMES);
        game.setGameid(newGame.getGameid());
        game.setIcon(newGame.getIcon());
        game.setTitle(newGame.getTitle());
        game.setDescription(newGame.getDescription());
        game.setLaunchdate(newGame.getLaunch_date());
        game.setPublisher(newGame.getPublisher());
        game.setScreen(newGame.getScreen());
        game.setPlatform(newGame.getPlatform());
        game.setGenre(newGame.getGenre());

    }

}
