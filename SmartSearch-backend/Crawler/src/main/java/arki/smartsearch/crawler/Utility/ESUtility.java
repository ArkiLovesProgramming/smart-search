package arki.smartsearch.crawler.Utility;

import arki.smartsearch.common.pojo.SofItem;
import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class ESUtility {
    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;

    private static final String INDEX = "se01";

    public void store() throws IOException, InterruptedException {
        BulkRequest request = new BulkRequest();
        request.timeout("1000s");    //???

        ArrayList<SofItem> SofItems = new ArrayList<>();
        SofItems = UrlUtility.generateSofItems_proxy();

        // 批处理请求， 修改，删除，只要在这里修改相应的请求就可以
        for (int i = 0; i < SofItems.size(); i++) {
            request.add(new IndexRequest(INDEX)
                    .id(SofItems.get(i).getQuestionId())
                    .source(JSON.toJSONString(SofItems.get(i)), XContentType.JSON));
        }

        BulkResponse bulkResponse = client.bulk(request, RequestOptions.DEFAULT);
        //是否失败，返回false表示成功
        System.out.println(bulkResponse.hasFailures());
    }

}
