import jakarta.persistence.*;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Entity
public class PlaySessionJPA  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date date;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="host")
    private PlayerJPA host;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="boardgame_url")
    private BoardGameJPA game;
    @ManyToMany
    @JoinTable
    private Collection<PlayerJPA> players;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="winner")
    private PlayerJPA winner;
    private int playtime;

    public PlaySessionJPA(){
        players = new ArrayList<>();
    }

    public PlaySessionJPA(Date date, PlayerJPA host, BoardGameJPA game, int playtime, Collection<PlayerJPA> players, PlayerJPA winner){
        this.date = date;
        this.host = host;
        this.game = game;
        this.playtime = playtime;
        this.players = players;
        this.winner = winner;
    }


    public Date getDate() {
        return date;
    }


    public PlayerJPA getHost() {
        return host;
    }


    public BoardGameJPA getGame() {
        return game;
    }


    public Collection<PlayerJPA> getAllPlayers() {
        return players;
    }


    public PlayerJPA getWinner() {
        return winner;
    }


    public int getPlaytime() {
        return playtime;
    }


    public String toVerboseString() {
        String result = game.toString() + " {";
        result = result + "\n  Date: " + date.toString();
        result = result + "\n  Playtime: " + playtime;
        result = result + "\n  Host: " + host.toString();
        result = result + "\n  Players: ";
        for (PlayerJPA player : players) {
            result = result + player.toString() + "; ";
        }
        result = result + "\n}\n";
        return result;
    }
}
