package com.webapp.tas.controllers;

import com.webapp.tas.errors.NotUniqueExcetpion;
import com.webapp.tas.objects.Game;
import com.webapp.tas.objects.GameShortDto;
import com.webapp.tas.objects.RankingDto;
import com.webapp.tas.tables.records.GamesRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.webapp.tas.Tables.REVIEWS;
import static org.jooq.impl.DSL.*;
import static com.webapp.tas.Tables.GAMES;

import java.util.List;

@RestController
public class GameController {

    @Autowired
    private DSLContext jooq;


    /**
     * Returns list of all games, returns ShortDto (GameID, Title & Icon)
     * @return
     */
    @GetMapping("/games")
    public List<GameShortDto> gamesList(){
        return jooq.select(GAMES.GAMEID, GAMES.TITLE, GAMES.ICON).from(GAMES).fetchInto(GameShortDto.class);
    }

    /**
     * returns game by its title
     * @param title
     * @return
     */
    @GetMapping("/games/title/{title}")
    public List<GameShortDto> getGameByTitle(@PathVariable String title){
        return jooq.select(GAMES.GAMEID, GAMES.TITLE, GAMES.ICON)
                .from(GAMES)
                .where(GAMES.TITLE.eq(title))
                .fetchInto(GameShortDto.class);
    }

    @GetMapping("/games/{id}")
    public List<Game> getGameByID(@PathVariable int id){
//        Game game = new Game();
//        GamesRecord gameRecord = jooq.fetchOne(GAMES, GAMES.GAMEID.eq(id));
//        game.setGameid(gameRecord.getGameid());
//        game.setIcon(gameRecord.getIcon());
//        game.setTitle(gameRecord.getTitle());
//        game.setDescription(gameRecord.getDescription());
//        game.setLaunch_date(gameRecord.getLaunchdate());
//        game.setPublisher(gameRecord.getPublisher());
//        game.setScreen(gameRecord.getScreen());
//        game.setPlatform(gameRecord.getPlatform());
//        game.setGenre(gameRecord.getGenre());
//        return game;
        return jooq.select(GAMES.GAMEID, GAMES.ICON, GAMES.TITLE, GAMES.DESCRIPTION, (GAMES.LAUNCHDATE).as("launch_date"),
                GAMES.PUBLISHER, GAMES.SCREEN, GAMES.PLATFORM, GAMES.GENRE, avg(REVIEWS.RATE).as("score"))
                .from(GAMES)
                .leftOuterJoin(REVIEWS).on(REVIEWS.GAMEID.eq(GAMES.GAMEID))
                .where(GAMES.GAMEID.eq(id))
                .groupBy(GAMES.GAMEID, GAMES.ICON, GAMES.TITLE, GAMES.DESCRIPTION,
                        GAMES.PUBLISHER, GAMES.SCREEN, GAMES.PLATFORM, GAMES.GENRE)
                .fetchInto(Game.class);
    }

    //TODO jakos ogarnac te obrazki

    @GetMapping("/games/ranking")
    public List<RankingDto> gameRanking(){
        return jooq.select(avg(REVIEWS.RATE).as("score"), GAMES.TITLE, GAMES.GAMEID).from(REVIEWS)
                .join(GAMES).on(GAMES.GAMEID.eq(REVIEWS.GAMEID))
                .groupBy(GAMES.TITLE, GAMES.GAMEID)
                .orderBy(one().desc())
                .fetchInto(RankingDto.class);
                //.fetch().formatJSON();
    }

    /**
     * ATM any images must be given as url to the source
     * Method of validating admin permissions needs to be added
     * GameId validation method needs to be added
     * @param newGame
     */
    @PostMapping("/addgame")
    public HttpStatus addGame(@RequestBody Game newGame){
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
        try {
            game.store();
        }catch (Exception e){
            throw new NotUniqueExcetpion("GameID is not unique");
        }
        return HttpStatus.CREATED;
    }
//TODO error handling
//TODO autoryzacja
}
