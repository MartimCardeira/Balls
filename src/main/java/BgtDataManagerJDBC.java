//import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BgtDataManager;
//import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;
//import tudelft.wis.idm_tasks.boardGameTracker.interfaces.PlaySession;
//import tudelft.wis.idm_tasks.boardGameTracker.interfaces.Player;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Date;
//import java.util.UUID;
//
//public class BgtDataManagerJDBC implements BgtDataManager {
//
//    private static Connection connection;
//    /**
//     * Implementation of the JDBCManager interface
//     * @return connection to the IMDB database
//     * @throws SQLException throws SQLException
//     */
//    public Connection getConnection() throws SQLException {
//        if (connection == null) {
//            String url = "jdbc:postgresql://localhost:5432/boardgametracker";
//            String user = "postgres";
//            String password = "postgres";
//
//            connection = DriverManager.getConnection(url, user, password);
//        }
//        return connection;
//    }
//
//    /**
//     * Creates a new player and stores it in the DB.
//     *
//     * @param name     the player name
//     * @param nickname the player nickname
//     * @return the new player
//     */
//    @Override
//    public Player createNewPlayer(String name, String nickname) {
//        PlayerJDBC player = new PlayerJDBC(name, nickname);
//        try (PreparedStatement query = getConnection().prepareStatement(
//                "INSERT INTO players(uuid, name, nickname) " +
//                        "VALUES(?, ?, ?);")) {
//            query.setString(1, player.getUuid().toString());
//            query.setString(2, player.getPlayerName());
//            query.setString(3, player.getPlayerNickName());
//            query.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return player;
//    }
//
//    /**
//     * Searches for player in the database by a substring of their name.
//     *
//     * @param name the name substring to use, e.g., searching for "hris" will find "Christoph"
//     * @return collection of all players containing the param substring in their names
//     */
//    @Override
//    public Collection<Player> findPlayersByName(String name) throws SQLException {
//        Connection db = getConnection();
//        String query = "SELECT * FROM players WHERE name = ?";
//        PreparedStatement selectTitles = db.prepareStatement(query);
//        selectTitles.setString(1, name);
//        ResultSet results = selectTitles.executeQuery();
//        Collection<Player> result = new ArrayList<>();
//        while (results.next()) {
//            String namee = results.getString("name");
//            String nickname = results.getString("nickname");
//            UUID uuid = UUID.fromString(results.getString("UUID"));
//            Collection<BoardGame> games = findBGbyUUID(uuid);
//            result.add(new PlayerJDBC(namee, nickname, uuid, games));
//        }
//        return result;
//    }
//
//    public Collection<BoardGame> findBGbyUUID(UUID uuid) throws SQLException {
//        Connection db = getConnection();
//        String query = "SELECT bg.name, bg.bgg_url FROM boardgames bg JOIN game_collections gc ON gc.boardgame = bg.bgg_url WHERE gc.player = ?";
//        PreparedStatement selectTitles = db.prepareStatement(query);
//        selectTitles.setString(1, uuid.toString());
//        ResultSet results = selectTitles.executeQuery();
//        Collection<BoardGame> result = new ArrayList<>();
//        while (results.next()) {
//            String name = results.getString("name");
//            String BGG_URL = results.getString("bgg_url");
//            result.add(new BoardGameJDBC(name, BGG_URL));
//        }
//        return result;
//    }
//
//    /**
//     * Creates a new board game and stores it in the DB.
//     * <p>
//     * Note: These "create" methods are somewhat unnecessary. However, I put
//     * them here to make the task test a bit easier. You can call an appropriate
//     * persist method for this.
//     *
//     * @param name   the name of the game
//     * @param bggURL the URL of the game at BoardGameGeek.com
//     * @return the new game
//     */
//    @Override
//    public BoardGame createNewBoardgame(String name, String bggURL) {
//        try(PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO boardgames (bgg_url, name) VALUES (?, ?)\n" +
//                "ON CONFLICT (bgg_url) DO NOTHING;")) {
//            preparedStatement.setString(2, bggURL);
//            preparedStatement.setString(1, name);
//            preparedStatement.executeUpdate();
//        } catch(SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return new BoardGameJDBC(name, bggURL);
//    }
//
//    /**
//     * Searches for game in the database by a substring of their name.
//     *
//     * @param name the name substring to use, e.g., searching for "clips" will
//     *             find "Eclipse: Second Dawn of the Galaxy""
//     * @return collection of all boardgames containing the param substring in their names
//     */
//    @Override
//    public Collection<BoardGame> findGamesByName(String name) throws SQLException {
//        Connection db = getConnection();
//        String query = "SELECT * FROM boardgames WHERE name = ?";
//        PreparedStatement selectTitles = db.prepareStatement(query);
//        selectTitles.setString(1, name);
//        ResultSet results = selectTitles.executeQuery();
//        Collection<BoardGame> result = new ArrayList<>();
//        while (results.next()) {
//            String bgg_url = results.getString("bgg_url");
//            String namee = results.getString("name");
//            result.add(new BoardGameJDBC(bgg_url, namee));
//        }
//        return result;
//    }
//
//    /**
//     * Creates a new play session and stores it in the DB.
//     *
//     * @param date     the date of the session
//     * @param host     the session host
//     * @param game     the game which was played
//     * @param playtime the approximate playtime in minutes
//     * @param players  all players
//     * @param winner   the one player who won (NULL in case of no winner; multiple
//     *                 winners not supported)
//     * @return the new play session
//     */
//    @Override
//    public PlaySession createNewPlaySession(Date date, Player host, BoardGame game, int playtime, Collection<Player> players, Player winner) {
//        return null;
//    }
//
//    /**
//     * Finds all play sessions from a specific date
//     *
//     * @param date the date to search from
//     * @return collection of all play sessions from the param date
//     */
//    @Override
//    public Collection<PlaySession> findSessionByDate(Date date) {
//        return null;
//    }
//
//    /**
//     * Persists a given player to the DB. Note that this player might already exist and only needs an update :-)
//     *
//     * @param player the player
//     */
//    @Override
//    public void persistPlayer(Player player) {
//        PlayerJDBC playerJDBC = (PlayerJDBC) player;
//        try (PreparedStatement query = getConnection().prepareStatement(
//                "INSERT INTO players(uuid, name, nickname) " +
//                        "VALUES(?, ?, ?) " +
//                        "ON CONFLICT (uuid) " +
//                        "DO UPDATE " +
//                        "SET name = ?, nickname = ?;")) {
//            query.setString(1, playerJDBC.getUuid().toString());
//            query.setString(2, playerJDBC.getPlayerName());
//            query.setString(3, playerJDBC.getPlayerNickName());
//            query.setString(4, playerJDBC.getPlayerName());
//            query.setString(5, playerJDBC.getPlayerNickName());
//            query.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        try (PreparedStatement query = getConnection().prepareStatement(
//                "DELETE FROM game_collections " +
//                        "WHERE player=?;")) {
//            query.setString(1, playerJDBC.getUuid().toString());
//            query.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        for (BoardGame bg : player.getGameCollection()) {
//            try (PreparedStatement query = getConnection().prepareStatement(
//                    "INSERT INTO game_collections(player, boardgame) " +
//                            "VALUES(?, ?);")) {
//                query.setString(1, playerJDBC.getUuid().toString());
//                query.setString(2, bg.getBGG_URL());
//                query.executeUpdate();
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//    }
//
//    /**
//     * Persists a given session to the DB. Note that this session might already exist and only needs an update :-)
//     *
//     * @param session the session
//     */
//    @Override
//    public void persistPlaySession(PlaySession session) {
//
//    }
//
//    /**
//     * Persists a given game to the DB. Note that this game might already exist and only needs an update :-)
//     *
//     * @param game the game
//     */
//    @Override
//    public void persistBoardGame(BoardGame game) {
//        try(PreparedStatement preparedStatement = getConnection().prepareStatement("""
//        INSERT INTO boardgames
//        VALUES(?, ?)
//        ON CONFLICT (bgg_url) DO UPDATE
//        SET name = ?;
//""")) {
//            preparedStatement.setString(1, game.getBGG_URL());
//            preparedStatement.setString(2, game.getName());
//            preparedStatement.setString(3, game.getName());
//            preparedStatement.executeUpdate();
//        }
//        catch(SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}
