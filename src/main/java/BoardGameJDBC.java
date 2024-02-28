import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;

public class BoardGameJDBC implements BoardGame {
    private String name;
    private final String BGG_URL;

    public BoardGameJDBC(String name, String bggUrl) {
        this.name = name;
        BGG_URL = bggUrl;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getBGG_URL() {
        return null;
    }

    @Override
    public String toVerboseString() {
        return null;
    }
}
