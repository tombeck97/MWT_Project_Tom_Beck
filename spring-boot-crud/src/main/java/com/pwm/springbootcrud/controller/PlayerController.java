package com.pwm.springbootcrud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping("/players/{id}")
    public ResponseEntity<Spieler> getSpieler(@PathVariable long id){
        try {
            Optional<Spieler> player = playerRepository.findById(id);
            if(player.isPresent()) {
                return new ResponseEntity<>(player.get(),HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/players/{id}")
    public ResponseEntity<Spieler> udpateSpieler(@PathVariable long id, @RequestBody Spieler spieler){
        Optional<Spieler> spielerData = playerRepository.findById(id);
        if (spielerData.isPresent()) {
            Spieler _spieler = spielerData.get();
            _spieler.setPlayer(spieler.getPlayer());
            _spieler.setDescription(spieler.getPlayer());
            _spieler.setFull(spieler.isFull());

            return new ResponseEntity<>(playerRepository.save(_spieler), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<HttpStatus> deleteSpieler(@PathVariable long id){
        try {
            playerRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<HttpStatus> deleteAllSpieler() {
        try {
            playerRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
