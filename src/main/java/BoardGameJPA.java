import jakarta.persistence.*;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;

import java.util.Collection;
import java.util.UUID;

@Entity
public class BoardGameJPA implements BoardGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Collection<PlayerJPA> players;

    private String name;
    private String bgg_url;

    /**
     * Returns the game name.
     *
     * @return game name
     */
    @Override
    public String getName() {
        return null;
    }

    /**
     * Returns the game's BoardGamesGeek.com URL.
     *
     * @return the URL as a string
     */
    @Override
    public String getBGG_URL() {
        return null;
    }
}
