import jakarta.persistence.*;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.Player;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Entity
public class PlaySessionJPA implements tudelft.wis.idm_tasks.boardGameTracker.interfaces.PlaySession {

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
    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public Player getHost() {
        return host;
    }

    @Override
    public BoardGame getGame() {
        return game;
    }

    @Override
    public Collection<Player> getAllPlayers() {
        return players;
    }

    @Override
    public Player getWinner() {
        return winner;
    }

    @Override
    public int getPlaytime() {
        return playtime;
    }

    @Override
    public String toVerboseString() {
        return "to be implemented";
    }
}
