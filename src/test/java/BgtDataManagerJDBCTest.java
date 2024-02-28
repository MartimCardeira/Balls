import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class BgtDataManagerJDBCTest {
    BgtDataManagerJDBC dataManagerJDBC;
    @BeforeEach
    void setUp() {
        dataManagerJDBC = new BgtDataManagerJDBC();
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
}