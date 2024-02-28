import tudelft.wis.idm_tasks.boardGameTracker.BgtException;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BgtDataManager;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.PlaySession;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.Player;

import java.sql.*;
import java.util.Collection;
import java.util.Date;

public class BgtDataManagerJDBC implements BgtDataManager {

    private static Connection connection;
    /**
     * Implementation of the JDBCManager interface
     * @return connection to the IMDB database
     * @throws SQLException throws SQLException
     */
    public Connection getConnection() throws SQLException {
        if (connection == null) {
            String url = "jdbc:postgresql://localhost:5432/boardgametracker";
            String user = "postgres";
            String password = "postgres";

            connection = DriverManager.getConnection(url, user, password);
        }
        return connection;
    }

    /**
     * Creates a new player and stores it in the DB.
     *
     * @param name     the player name
     * @param nickname the player nickname
     * @return the new player
     */
    @Override
    public Player createNewPlayer(String name, String nickname) {
        PlayerJDBC player = new PlayerJDBC(name, nickname);
        try (PreparedStatement query = getConnection().prepareStatement(
                "INSERT INTO player(uuid, name, nickname) " +
                        "VALUES(?, ?, ?);")) {
            query.setString(1, player.getUuid().toString());
            query.setString(2, player.getPlayerName());
            query.setString(3, player.getPlayerNickName());
            query.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return player;
    }

    /**
     * Searches for player in the database by a substring of their name.
     *
     * @param name the name substring to use, e.g., searching for "hris" will find "Christoph"
     * @return collection of all players containing the param substring in their names
     * @throws BgtException the bgt exception
     */
    @Override
    public Collection<Player> findPlayersByName(String name) throws BgtException {
        return null;
    }

    /**
     * Creates a new board game and stores it in the DB.
     * <p>
     * Note: These "create" methods are somewhat unnecessary. However, I put
     * them here to make the task test a bit easier. You can call an appropriate
     * persist method for this.
     *
     * @param name   the name of the game
     * @param bggURL the URL of the game at BoardGameGeek.com
     * @return the new game
     * @throws BgtException DB trouble
     */
    @Override
    public BoardGame createNewBoardgame(String name, String bggURL) throws BgtException {
        return null;
    }

    /**
     * Searches for game in the database by a substring of their name.
     *
     * @param name the name substring to use, e.g., searching for "clips" will
     *             find "Eclipse: Second Dawn of the Galaxy""
     * @return collection of all boardgames containing the param substring in their names
     */
    @Override
    public Collection<BoardGame> findGamesByName(String name) throws BgtException {
        return null;
    }

    /**
     * Creates a new play session and stores it in the DB.
     *
     * @param date     the date of the session
     * @param host     the session host
     * @param game     the game which was played
     * @param playtime the approximate playtime in minutes
     * @param players  all players
     * @param winner   the one player who won (NULL in case of no winner; multiple
     *                 winners not supported)
     * @return the new play session
     */
    @Override
    public PlaySession createNewPlaySession(Date date, Player host, BoardGame game, int playtime, Collection<Player> players, Player winner) throws BgtException {
        return null;
    }

    /**
     * Finds all play sessions from a specific date
     *
     * @param date the date to search from
     * @return collection of all play sessions from the param date
     * @throws BgtException the bgt exception
     */
    @Override
    public Collection<PlaySession> findSessionByDate(Date date) throws BgtException {
        return null;
    }

    /**
     * Persists a given player to the DB. Note that this player might already exist and only needs an update :-)
     *
     * @param player the player
     */
    @Override
    public void persistPlayer(Player player) {

    }

    /**
     * Persists a given session to the DB. Note that this session might already exist and only needs an update :-)
     *
     * @param session the session
     */
    @Override
    public void persistPlaySession(PlaySession session) {

    }

    /**
     * Persists a given game to the DB. Note that this game might already exist and only needs an update :-)
     *
     * @param game the game
     */
    @Override
    public void persistBoardGame(BoardGame game) {

    }
}
