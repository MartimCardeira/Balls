/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import org.junit.jupiter.api.Test;
import org.tinylog.Logger;

import tudelft.wis.idm_tasks.boardGameTracker.BgtException;
import java.sql.SQLException;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The type POJO test.
 *
 * @author Christoph Lofi, Alexandra Neagu
 */
public class BgtDataManagerJPATest extends AbstractBGTDemo {

    /**
     * Instantiates a new POJO test.
     */
    public BgtDataManagerJPATest() {
    }

    private BgtDataManagerJPA dataManager = new BgtDataManagerJPA();

    @Override
    public BgtDataManagerJPA getBgtDataManager() {
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
        // Create dummy data
        Collection<PlaySessionJPA> testSessions = this.createDummyData(12, 6);

        for (PlaySessionJPA session : testSessions) {
            Logger.info("Session Created: \n" + session.toVerboseString());
        }

        // Get dummy session & related data
        PlaySessionJPA firstsession = testSessions.iterator().next();
        PlayerJPA host = firstsession.getHost();
        BoardGameJPA game = firstsession.getGame();

        // Retrieve the host from the database and check if it returns correctly
        PlayerJPA retrievedPlayer = this.getBgtDataManager().findPlayersByName(host.getPlayerName()).iterator().next();
        assertEquals(retrievedPlayer.getPlayerNickName(), retrievedPlayer.getPlayerNickName());
        assertEquals(retrievedPlayer.getGameCollection().size(), host.getGameCollection().size());
        Logger.info("Player check passed: " + retrievedPlayer.getPlayerName() + "; collectionSize: " + retrievedPlayer.getGameCollection().size());

        // Retrieve the game from the database and check if it returns correctly
        BoardGameJPA retrievedGame = this.getBgtDataManager().findGamesByName(game.getName()).iterator().next();
        assertEquals(retrievedGame.getBGG_URL(), game.getBGG_URL());

        // Retrieve session by date
        Collection<PlaySessionJPA> retrievedSession = this.getBgtDataManager().findSessionByDate(firstsession.getDate());
        assertEquals(firstsession.getDate(), retrievedSession.iterator().next().getDate());

        // Remove a game from the host's collection, add  it again
        BoardGameJPA firstGame = host.getGameCollection().iterator().next();
        int numOfGames = host.getGameCollection().size();
        host.getGameCollection().remove(firstGame);
        this.getBgtDataManager().persistPlayer(host);

        // Load the host again from DB
        PlayerJPA hostFromDB = this.getBgtDataManager().findPlayersByName(host.getPlayerName()).iterator().next();
        assertEquals(numOfGames - 1, hostFromDB.getGameCollection().size());

        // Add the game again
        hostFromDB.getGameCollection().add(firstGame);
        this.getBgtDataManager().persistPlayer(host);

        // Load the host again from DB
        PlayerJPA hostFromDB2 = this.getBgtDataManager().findPlayersByName(host.getPlayerName()).iterator().next();
        assertEquals(numOfGames, hostFromDB2.getGameCollection().size());

    }

}
