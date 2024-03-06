import jakarta.persistence.*;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.Player;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
public class PlayerJPA implements Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private String name;
    private String nickname;

    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<BoardGameJPA> boardGames;

    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<PlaySessionJPA> playSessions;
    /**
     * Returns the name of the player.
     *
     * @return name of the player
     */
    @Override
    public String getPlayerName() {
        return name;
    }

    /**
     * Returns the nickname of the player.
     *
     * @return nickname of the player
     */
    @Override
    public String getPlayerNickName() {
        return nickname;
    }

    /**
     * Returns all the boardgames this player owns (if any).
     *
     * @return collection of boardgames this player owns
     */
    @Override
    public Collection<BoardGameJPA> getGameCollection() {
        return boardGames;
    }
}
