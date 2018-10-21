package com.webapp.tas.controllers;

import com.webapp.tas.objects.Game;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

}
