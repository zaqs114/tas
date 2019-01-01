package com.webapp.tas.controllers;

import com.webapp.tas.errors.NotUniqueExcetpion;
import com.webapp.tas.objects.Game;
import com.webapp.tas.objects.GameShortDto;
import com.webapp.tas.objects.RankingDto;
import com.webapp.tas.tables.records.GamesRecord;
import org.jooq.DSLContext;
import org.jooq.meta.derby.sys.Sys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.webapp.tas.Tables.REVIEWS;
import static org.jooq.impl.DSL.*;
import static com.webapp.tas.Tables.GAMES;
import com.webapp.tas.s3.AmazonClient;

import java.sql.Date;
import java.util.List;

@RestController
public class GameController {

    @Autowired
    private DSLContext jooq;

    @Autowired
    private AmazonClient amazonClient;


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


    @GetMapping("/games/ranking")
    public List<RankingDto> gameRanking(){
        return jooq.select(avg(REVIEWS.RATE).as("score"), GAMES.TITLE, GAMES.GAMEID).from(REVIEWS)
                .join(GAMES).on(GAMES.GAMEID.eq(REVIEWS.GAMEID))
                .groupBy(GAMES.TITLE, GAMES.GAMEID)
                .orderBy(one().desc())
                .fetchInto(RankingDto.class);
                //.fetch().formatJSON();
    }

    @PostMapping("/addgame")
    public HttpStatus addGame(/*@RequestBody Game newGame*/
            @RequestParam(value = "gameid") int gameid,
            @RequestParam(value = "icon", required = false) MultipartFile iconfile,
            @RequestParam(value = "title") String title,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "launch_date") Date launch_date,
            @RequestParam(value = "publisher") String publisher,
            @RequestParam(value = "screen", required = false) MultipartFile screenfile,
            @RequestParam(value = "platform") String platform,
            @RequestParam(value = "genre") String genre){
        GamesRecord game = jooq.newRecord(GAMES);
//        game.setGameid(newGame.getGameid());
//        game.setIcon(newGame.getIcon());
//        game.setTitle(newGame.getTitle());
//        game.setDescription(newGame.getDescription());
//        game.setLaunchdate(newGame.getLaunch_date());
//        game.setPublisher(newGame.getPublisher());
//        game.setScreen(newGame.getScreen());
//        game.setPlatform(newGame.getPlatform());
//        game.setGenre(newGame.getGenre());

        game.setGameid(gameid);
        game.setTitle(title);
        game.setDescription(description);
        game.setLaunchdate(launch_date);
        game.setPublisher(publisher);
        game.setPlatform(platform);
        game.setGenre(genre);

        if (iconfile != null){
            game.setIcon(amazonClient.uploadFile(iconfile)); //set icon
        }
        else{
            game.setIcon("");
        }

        if (screenfile != null){
            game.setScreen(amazonClient.uploadFile(screenfile)); //set screen
        }
        else{
            game.setScreen("");
        }

        try {
            game.store();
        }catch (Exception e){
            throw new NotUniqueExcetpion("GameID is not unique");
        }
        return HttpStatus.CREATED;
    }
    //TODO autoinkrementacja
//TODO error handling
}
