package arki.smartsearch.sssearchserver;

import arki.smartsearch.sssearchserver.dao.UserDao;
import arki.smartsearch.sssearchserver.utility.ESUtility;
import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.sql.SQLException;

@SpringBootTest
class SsSearchServerApplicationTests {

    @Autowired
    private ESUtility utility;

    @Autowired
    private UserDao userDao;

    @Test
    void contextLoads() throws IOException, SQLException {
        utility.searchByKeyword_title("I want to learn");
    }

}
