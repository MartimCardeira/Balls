import tudelft.wis.idm_tasks.basicJDBC.interfaces.JDBCManager;
import tudelft.wis.idm_tasks.basicJDBC.interfaces.JDBCTask2Interface;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class QueryMachine implements JDBCManager, JDBCTask2Interface {

    @Override
    public Connection getConnection() throws SQLException {
         Connection db = DriverManager.getConnection("jdbc:postgresql://localhost:5432/imdb",
                 "postgres", "malc1995");
         return db;
    }

    @Override
    public Collection<String> getTitlesPerYear(int year) throws SQLException {
        Connection db = getConnection();
        String query = "SELECT primary_title FROM titles WHERE start_year = ?";
        PreparedStatement selectTitles = db.prepareStatement(query);
        selectTitles.setInt(1, year);
        ResultSet results = selectTitles.executeQuery();
        Collection<String> result = new ArrayList<>();
        while (results.next()) {
            String value = results.getString("primary_title");
            result.add(value);
        }
        return result;
    }

    @Override
    public Collection<String> getJobCategoriesFromTitles(String searchString) throws SQLException {
        Connection db = getConnection();
        String query = "SELECT DISTINCT c.job_category " +
                "FROM titles t " +
                "JOIN cast_info c " +
                "ON t.title_id = c.title_id " +
                "WHERE t.primary_title " +
                "LIKE ?";
        PreparedStatement selectTitles = db.prepareStatement(query);
        selectTitles.setString(1, "%" + searchString + "%");
        ResultSet results = selectTitles.executeQuery();
        Collection<String> result = new ArrayList<>();
        while (results.next()) {
            String value = results.getString("job_category");
            result.add(value);
        }
        return result;
    }

    @Override
    public double getAverageRuntimeOfGenre(String genre) {
        return 0;
    }

    @Override
    public Collection<String> getPlayedCharacters(String actorFullname) {
        return null;
    }
}
