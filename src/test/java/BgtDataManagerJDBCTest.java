import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
<<<<<<< HEAD
import tudelft.wis.idm_tasks.boardGameTracker.BgtException;
=======
>>>>>>> 2a51af8f4f2980fe3778ee277bf6c3073c8a48f5
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.Player;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class BgtDataManagerJDBCTest {
    BgtDataManagerJDBC dataManagerJDBC;
    @BeforeEach
    void setUp() {
        dataManagerJDBC = new BgtDataManagerJDBC();
        try {
            dataManagerJDBC.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getConnection() {
        try {
            Connection connection = dataManagerJDBC.getConnection();
            assertNotNull(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void createNewPlayer() {
        Player player = dataManagerJDBC.createNewPlayer("Adam", "addis");
        assertEquals("Adam", player.getPlayerName());
        assertEquals("addis", player.getPlayerNickName());
    }

    @Test
    void createNewBoardGameTest() {
        BoardGameJDBC bgjdbc = new BoardGameJDBC("CATAN", "https://boardgamegeek.com/geeksearch.php?action=search&q=catan&objecttype=boardgame");
        assertEquals(bgjdbc, dataManagerJDBC.createNewBoardgame("CATAN", "https://boardgamegeek.com/geeksearch.php?action=search&q=catan&objecttype=boardgame"));
    }

    @Test
    void addAndfindByNamePlayerTest() throws SQLException, BgtException {
        dataManagerJDBC.createNewPlayer("Thomas", "Tom");
        Collection<Player> c = dataManagerJDBC.findPlayersByName("Thomas");
        Iterator<Player> i = c.iterator();
        Player p = i.next();
        assertTrue(p.getPlayerName().equals("Thomas"));
    }
}