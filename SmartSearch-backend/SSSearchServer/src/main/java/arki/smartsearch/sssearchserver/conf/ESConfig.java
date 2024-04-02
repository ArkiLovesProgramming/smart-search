package arki.smartsearch.sssearchserver.conf;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class ESConfig {
    @Bean("myRestHighLevelClient")
    public RestHighLevelClient restHighLevelClient() {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
//                        new HttpHost("localhost", 9200, "http")
                        new HttpHost("3.99.156.162", 9200, "http")
                )
        );
        return client;
    }

}
