///*
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import tudelft.wis.idm_tasks.boardGameTracker.BgtException;
//import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;
//import tudelft.wis.idm_tasks.boardGameTracker.interfaces.Player;
//
//import java.sql.Array;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Iterator;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class BgtDataManagerJDBCTest {
//    BgtDataManagerJDBC dataManagerJDBC;
//    @BeforeEach
//    void setUp() {
//        dataManagerJDBC = new BgtDataManagerJDBC();
//        try {
//            dataManagerJDBC.getConnection();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Test
//    void getConnection() {
//        try {
//            Connection connection = dataManagerJDBC.getConnection();
//            assertNotNull(connection);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Test
//    void createNewPlayer() {
//        Player player = dataManagerJDBC.createNewPlayer("Adam", "addis");
//        assertEquals("Adam", player.getPlayerName());
//        assertEquals("addis", player.getPlayerNickName());
//    }
//
//    @Test
//    void createNewBoardGameTest() {
//        BoardGameJDBC bgjdbc = new BoardGameJDBC("CATAN", "https://boardgamegeek.com/geeksearch.php?action=search&q=catan&objecttype=boardgame");
//        assertEquals(bgjdbc, dataManagerJDBC.createNewBoardgame("CATAN", "https://boardgamegeek.com/geeksearch.php?action=search&q=catan&objecttype=boardgame"));
//    }
//
//    @Test
//    void addAndfindByNamePlayerTest() throws SQLException, BgtException {
//        dataManagerJDBC.createNewPlayer("Thomas", "Tom");
//        Collection<Player> c = dataManagerJDBC.findPlayersByName("Thomas");
//        Iterator<Player> i = c.iterator();
//        Player p = i.next();
//        assertTrue(p.getPlayerName().equals("Thomas"));
//    }
//
//    @Test
//    void createAndFindBoardGameTest() throws SQLException {
//        dataManagerJDBC.createNewBoardgame("g", "google.com");
//        Collection<BoardGame> c = dataManagerJDBC.findGamesByName("g");
//        Iterator<BoardGame> i = c.iterator();
//        BoardGame b = i.next();
//        assertEquals("google.com", b.getBGG_URL());
//        assertEquals("g", b.getName());
//    }
//
//    @Test
//    void persistPlayer() {
//        PlayerJDBC playerJDBC = new PlayerJDBC("Brad", "Bard");
//        dataManagerJDBC.persistPlayer(playerJDBC);
//        try {
//            Collection<Player> players = dataManagerJDBC.findPlayersByName("Brad");
//            assertTrue(players.contains(playerJDBC));
//            playerJDBC.setName("Chad");
//            playerJDBC.setNickName("GigaChad");
//            dataManagerJDBC.persistPlayer(playerJDBC);
//            players = dataManagerJDBC.findPlayersByName("Chad");
//            assertTrue(players.contains(playerJDBC));
//            players = dataManagerJDBC.findPlayersByName("Brad");
//            assertEquals(0, players.size());
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//}*/
