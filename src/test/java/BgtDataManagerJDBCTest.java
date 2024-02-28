import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.Player;

import java.sql.Connection;
import java.sql.SQLException;

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
}