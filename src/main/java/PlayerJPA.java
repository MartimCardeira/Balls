import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
public class PlayerJPA{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String nickname;

    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<BoardGameJPA> boardGames;

    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<PlaySessionJPA> playSessions;

    public PlayerJPA(String name, String nickname) {
        this.name = name;
        this.nickname = nickname;
        boardGames = new ArrayList<>();
        playSessions = new ArrayList<>();
    }

    public PlayerJPA() {

    }
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

    public String toVerboseString() {
        String result = name;
        if (nickname != null) {
            result = result + " (" + nickname + ")";
        }
        return result;
    }
}
