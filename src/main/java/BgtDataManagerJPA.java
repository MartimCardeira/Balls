import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import tudelft.wis.idm_tasks.boardGameTracker.BgtException;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

public class BgtDataManagerJPA  {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public BgtDataManagerJPA() {
        entityManagerFactory = Persistence.createEntityManagerFactory("idm.BoardGameTracker");
        entityManager = entityManagerFactory.createEntityManager();
    }

    /**
     * Creates a new player and stores it in the DB.
     *
     * @param name     the player name
     * @param nickname the player nickname
     * @return the new player
     * @throws SQLException DB trouble
     */

    public PlayerJPA createNewPlayer(String name, String nickname) throws BgtException {
        PlayerJPA player = new PlayerJPA(name, nickname);

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(player);
        transaction.commit();

        return player;
    }

    /**
     * Searches for player in the database by a substring of their name.
     *
     * @param name the name substring to use, e.g., searching for "hris" will find "Christoph"
     * @return collection of all players containing the param substring in their names
     * @throws BgtException the bgt exception
     */

    public Collection<PlayerJPA> findPlayersByName(String name) throws BgtException, SQLException {
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
     * @throws SQLException DB trouble
     */

    public BoardGameJPA createNewBoardgame(String name, String bggURL) throws BgtException {
        return null;
    }

    /**
     * Searches for game in the database by a substring of their name.
     *
     * @param name the name substring to use, e.g., searching for "clips" will
     *             find "Eclipse: Second Dawn of the Galaxy""
     * @return collection of all boardgames containing the param substring in their names
     */

    public Collection<BoardGameJPA> findGamesByName(String name) throws BgtException, SQLException {
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

    public PlaySessionJPA createNewPlaySession(Date date, PlayerJPA host, BoardGameJPA game, int playtime, Collection<PlayerJPA> players, PlayerJPA winner) throws BgtException {
        return null;
    }

    /**
     * Finds all play sessions from a specific date
     *
     * @param date the date to search from
     * @return collection of all play sessions from the param date
     * @throws BgtException the bgt exception
     */
    public Collection<PlaySessionJPA> findSessionByDate(Date date) throws BgtException {
        return null;
    }

    /**
     * Persists a given player to the DB. Note that this player might already exist and only needs an update :-)
     *
     * @param player the player
     */
    public void persistPlayer(PlayerJPA player) {

    }

    /**
     * Persists a given session to the DB. Note that this session might already exist and only needs an update :-)
     *
     * @param session the session
     */
    public void persistPlaySession(PlaySessionJPA session) {

    }

    /**
     * Persists a given game to the DB. Note that this game might already exist and only needs an update :-)
     *
     * @param game the game
     */
    public void persistBoardGame(BoardGameJPA game) {

    }
}
