package com.pwm.springbootcrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pwm.springbootcrud.entity.Spieler;
import com.pwm.springbootcrud.repository.PlayerRepository;

@RestController
@RequestMapping("/api")
public class PlayerController {
    private PlayerRepository playerRepository;
    @Autowired
    public PlayerController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @PostMapping("/players")
    public ResponseEntity<Spieler> createSpieler(@RequestBody Spieler spieler){
        try{
            Spieler newSpieler = playerRepository.save(new Spieler(spieler.getPlayer(), spieler.getDescription(), spieler.isFull()));
            return new ResponseEntity<>(newSpieler, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/players")
    public ResponseEntity<List<Spieler>> getAllSpieler() {
        try {
            List<Spieler> spielerList = playerRepository.findAll();
            if(spielerList==null || spielerList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(spielerList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
