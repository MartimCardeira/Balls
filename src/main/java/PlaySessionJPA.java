import jakarta.persistence.*;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.Player;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Entity
public class PlaySessionJPA  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
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

    public Date getDate() {
        return date;
    }


    public PlayerJPA getHost() {
        return host;
    }


    public BoardGame getGame() {
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
        return "to be implemented";
    }
}
