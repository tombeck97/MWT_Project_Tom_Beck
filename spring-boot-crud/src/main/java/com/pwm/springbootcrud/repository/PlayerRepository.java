package com.pwm.springbootcrud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pwm.springbootcrud.entity.Spieler;

public interface PlayerRepository extends JpaRepository<Spieler, Long>{
    public List<Spieler> findbyFull(boolean full);

}
