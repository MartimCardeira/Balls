import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.Player;

import java.util.Collection;

public class PlayerJDBC implements Player {
    private String name;
    private String nickname;
    private Collection<BoardGame> getGameCollection;

    public PlayerJDBC(String name, String nickname, Collection<BoardGame> getGameCollection) {
        this.name = name;
        this.nickname = nickname;
        this.getGameCollection = getGameCollection;
    }

    @Override
    public String getPlayerName() {
        return null;
    }

    @Override
    public String getPlayerNickName() {
        return null;
    }

    @Override
    public Collection<BoardGame> getGameCollection() {
        return null;
    }

    @Override
    public String toVerboseString() {
        return null;
    }
}
