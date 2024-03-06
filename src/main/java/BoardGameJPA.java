import jakarta.persistence.*;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.Player;

import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

@Entity
public class BoardGameJPA implements BoardGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @ManyToMany(fetch = FetchType.LAZY)
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
        return this.name;
    }

    /**
     * Returns the game's BoardGamesGeek.com URL.
     *
     * @return the URL as a string
     */
    @Override
    public String getBGG_URL() {
        return this.bgg_url;
    }

   public String toVerboseString() {
        return "This is the game with URL: " + bgg_url + " and name: " + name;
   }
}


