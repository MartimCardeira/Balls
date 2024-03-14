import jakarta.persistence.*;

import java.util.Collection;
import java.util.UUID;

@Entity
public class BoardGameJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<PlayerJPA> players;

    private String name;
    private String bgg_url;



    public BoardGameJPA(UUID id, Collection<PlayerJPA> players, String name, String bgg_url) {
        this.id = id;
        this.players = players;
        this.name = name;
        this.bgg_url = bgg_url;
    }


    public BoardGameJPA() {
    }

    /**
     * Returns the game name.
     *
     * @return game name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the game's BoardGamesGeek.com URL.
     *
     * @return the URL as a string
     */
    public String getBGG_URL() {
        return this.bgg_url;
    }

   public String toVerboseString() {
        return "This is the game with URL: " + bgg_url + " and name: " + name;
   }
}

