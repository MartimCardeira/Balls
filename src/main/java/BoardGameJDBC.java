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
        return name;
    }

    @Override
    public String getBGG_URL() {
        return BGG_URL;
    }

    @Override
    public String toVerboseString() {
        return name + " (" + BGG_URL + ")";
    }
}
