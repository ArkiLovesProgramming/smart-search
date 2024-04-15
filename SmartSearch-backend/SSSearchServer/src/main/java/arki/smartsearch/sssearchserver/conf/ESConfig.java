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
                        new HttpHost("smartsearch-es-lb-1387008801.ca-central-1.elb.amazonaws.com", 9200, "http")
                )
        );
        return client;
    }

}
