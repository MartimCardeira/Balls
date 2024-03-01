import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardGameJDBC that = (BoardGameJDBC) o;
        return Objects.equals(name, that.name) && Objects.equals(BGG_URL, that.BGG_URL);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, BGG_URL);
    }
}
