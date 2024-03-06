import org.junit.jupiter.api.Test;
import org.tinylog.Logger;
import tudelft.wis.idm_solutions.BoardGameTracker.POJO_Implementation.BgtDataManager_POJO;
import tudelft.wis.idm_tasks.boardGameTracker.BgtException;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BgtDataManager;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.PlaySession;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.Player;

import java.sql.SQLException;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class JDBC_Test {

    /**
     * Instantiates a new JDBC test.
     */
    public JDBC_Test() {
    }

    private BgtDataManagerJDBC dataManager = new BgtDataManagerJDBC();

    public BgtDataManager getBgtDataManager() {
        return dataManager;
    }

    /**
     * Just runs the application with some simple queries and assertions. It's
     * not very comprehensive, essentially, just a single session is retrieved
     * and the hist and the game is being checked.
     */
    @Test
    public void basicTest() throws BgtException, SQLException {

        // Make sure to start this test with an empty DB - trivial for POJO though...

        // Get dummy session & related data
        Player host = new PlayerJDBC("Tom", "Jerry");
        BoardGame game = new BoardGameJDBC("google.com", "Dinosaur Game");
        host.getGameCollection().add(game);
        getBgtDataManager().persistBoardGame(game);
        getBgtDataManager().persistPlayer(host);

        // Retrieve the host from the database and check if it returns correctly
        Player retrievedPlayer = this.getBgtDataManager().findPlayersByName(host.getPlayerName()).iterator().next();
        assertEquals(host.getPlayerNickName(), retrievedPlayer.getPlayerNickName());
        assertEquals(host.getGameCollection().size(), retrievedPlayer.getGameCollection().size());
        Logger.info("Player check passed: " + retrievedPlayer.getPlayerName() + "; collectionSize: " + retrievedPlayer.getGameCollection().size());

        // Retrieve the game from the database and check if it returns correctly
        BoardGame retrievedGame = this.getBgtDataManager().findGamesByName(game.getName()).iterator().next();
        assertEquals(retrievedGame.getBGG_URL(), game.getBGG_URL());

        // Remove a game from the host's collection, add  it again
        BoardGame firstGame = host.getGameCollection().iterator().next();
        int numOfGames = host.getGameCollection().size();
        host.getGameCollection().remove(firstGame);
        this.getBgtDataManager().persistPlayer(host);

        // Load the host again from DB
        Player hostFromDB = this.getBgtDataManager().findPlayersByName(host.getPlayerName()).iterator().next();
        assertEquals(numOfGames - 1, hostFromDB.getGameCollection().size());

        // Add the game again
        host.getGameCollection().add(firstGame);
        this.getBgtDataManager().persistPlayer(host);

        // Load the host again from DB
        Player hostFromDB2 = this.getBgtDataManager().findPlayersByName(host.getPlayerName()).iterator().next();
        assertEquals(numOfGames, hostFromDB2.getGameCollection().size());

    }

}
