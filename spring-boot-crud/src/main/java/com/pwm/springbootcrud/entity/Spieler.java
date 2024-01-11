package  com.pwm.springbootcrud.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "players")
public class Spieler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String player;
    private String description;
    private boolean full;

    public Spieler(){

    }

    public Spieler(String player, String description, boolean full) {
        this.player = player;
        this.description = description;
        this.full = full; 
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getPlayer(){
        return player;
    }

    public void setPlayer(String player){
        this.player = player;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public boolean isFull(){
        return full;
    }

    public void setFull(boolean full){
        this.full = full;
    }

}
