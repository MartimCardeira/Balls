import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QueryMachineTest {


    @Test
    void testQuery1() throws SQLException {
        QueryMachine q = new QueryMachine();
        Collection<String> list = q.getTitlesPerYear(2002);
        for(String s : list){
            System.out.println(s);
        }
    }

    @Test
    void testQuery2() throws SQLException{
        QueryMachine q = new QueryMachine();
        Collection<String> list = q.getJobCategoriesFromTitles("Batman");
        for(String s : list){
            System.out.println(s);
        }
    }


}