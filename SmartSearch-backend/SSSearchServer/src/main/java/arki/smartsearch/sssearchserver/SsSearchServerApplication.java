package arki.smartsearch.sssearchserver;

import arki.smartsearch.sssearchserver.dao.UserDao;
import arki.smartsearch.sssearchserver.utility.ESUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.io.IOException;

@SpringBootApplication
public class SsSearchServerApplication {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(SsSearchServerApplication.class, args);
    }


}
