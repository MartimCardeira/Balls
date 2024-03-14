import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
public class PlayerJPA{

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
    public String getPlayerName() {
        return name;
    }

    /**
     * Returns the nickname of the player.
     *
     * @return nickname of the player
     */
    public String getPlayerNickName() {
        return nickname;
    }

    /**
     * Returns all the boardgames this player owns (if any).
     *
     * @return collection of boardgames this player owns
     */
    public Collection<BoardGameJPA> getGameCollection() {
        return boardGames;
    }
}