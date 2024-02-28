import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.Player;

import java.util.Collection;
import java.util.LinkedList;
import java.util.UUID;

import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;

/**
 * POJO (Plain Old Java Object) Implementation without any database
 * functionality.
 *
 * @author Christoph Lofi, Alexandra Neagu
 */
public class PlayerJDBC implements Player {
    private UUID uuid;
    private String name;
    private String nickName;
    private Collection<BoardGame> gameCollection;

    /**
     * Instantiates a new Player POJO.
     *
     * @param name     name
     * @param nickName nickname
     */
    public PlayerJDBC(String name, String nickName) {
        this.name = name;
        this.nickName = nickName;
        this.uuid = UUID.randomUUID();
        gameCollection = new LinkedList<>();
    }

    public PlayerJDBC(String name, String nickName, UUID uuid, Collection<BoardGame> gameCollection) {
        this.name = name;
        this.nickName = nickName;
        this.uuid = uuid;
        this.gameCollection = gameCollection;
    }

    public UUID getUuid() {
        return uuid;
    }

    @Override
    public String getPlayerName() {
        return name;
    }

    @Override
    public String getPlayerNickName() {
        return nickName;
    }

    @Override
    public Collection<BoardGame> getGameCollection() {
        return gameCollection;
    }


    @Override
    public String toVerboseString() {
        String result = name;
        if (nickName != null) {
            result = result + " (" + nickName + ")";
        }
        return result;
    }

}
