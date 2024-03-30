package arki.smartsearch.crawler;

import arki.smartsearch.crawler.Utility.ESUtility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class CrawlerApplicationTests {

    @Autowired
    private ESUtility esUtility;
    @Test
    void contextLoads() throws IOException, InterruptedException {
        esUtility.store();
    }

}
