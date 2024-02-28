import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.Player;

import java.util.Collection;

public class PlayerJDBC implements Player {
    private String name;
    private String nickname;
    private Collection<BoardGame> gameCollection;

    public PlayerJDBC(String name, String nickname, Collection<BoardGame> getGameCollection) {
        this.name = name;
        this.nickname = nickname;
        this.gameCollection = getGameCollection;
    }

    @Override
    public String getPlayerName() {
        return name;
    }

    @Override
    public String getPlayerNickName() {
        return nickname;
    }

    @Override
    public Collection<BoardGame> getGameCollection() {
        return gameCollection;
    }

    @Override
    public String toVerboseString() {
        return null;
    }
}
