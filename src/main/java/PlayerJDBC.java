import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.Player;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Objects;
import java.util.UUID;

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
    public Collection<BoardGameJPA> getGameCollection() {
        return gameCollection;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        String result = name;
        if (nickName != null) {
            result = result + " (" + nickName + ")";
        }
        result += gameCollection.toString();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerJDBC that = (PlayerJDBC) o;
        return Objects.equals(uuid, that.uuid) && Objects.equals(name, that.name) && Objects.equals(nickName, that.nickName) && Objects.equals(gameCollection, that.gameCollection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, name, nickName, gameCollection);
    }
}
