package com.webapp.tas.controllers;

import com.webapp.tas.objects.Game;
import com.webapp.tas.tables.records.GamesRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.webapp.tas.Tables.REVIEWS;
import static org.jooq.impl.DSL.*;
import static com.webapp.tas.Tables.GAMES;

import java.util.List;

@RestController
public class GameController {

    @Autowired
    private DSLContext jooq;

    @GetMapping("/games")
    public List<Game> gamesList(){
        return jooq.select(GAMES.GAMEID, GAMES.TITLE, GAMES.ICON).from(GAMES).fetchInto(Game.class);
    }
    //TODO dorobic klase dla tego

    @GetMapping("/games/{id}")
    public Game getGameByID(@PathVariable int id){
        Game game = new Game();
        GamesRecord gameRecord = jooq.fetchOne(GAMES, GAMES.GAMEID.eq(id));
        game.setGameid(gameRecord.getGameid());
        game.setIcon(gameRecord.getIcon());
        game.setTitle(gameRecord.getTitle());
        game.setDescription(gameRecord.getDescription());
        game.setLaunch_date(gameRecord.getLaunchdate());
        game.setPublisher(gameRecord.getPublisher());
        game.setScreen(gameRecord.getScreen());
        game.setPlatform(gameRecord.getPlatform());
        game.setGenre(gameRecord.getGenre());
        //int test = game.getScore(id);
        return game;
    }
    //TODO sprobuj jakos sformatowac tego jsona

    @GetMapping("/games/ranking")
    public String gameRanking(){
        return jooq.select(avg(REVIEWS.RATE).as("score"), GAMES.TITLE).from(REVIEWS)
                .join(GAMES).on(GAMES.GAMEID.eq(REVIEWS.GAMEID))
                .groupBy(GAMES.TITLE)
                .orderBy(one().desc())
                .fetch()
                .formatJSON();
    }

    /**
     * Method of validating admin permissions needs to be added
     * GameId validation method needs to be added
     * launch_date format needs to be tested (java.sql vs java.utl?)
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
        game.store();
    }
//TODO error handling
//TODO autoryzacja
}
