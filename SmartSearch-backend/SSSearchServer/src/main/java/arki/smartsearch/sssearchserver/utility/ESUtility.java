package arki.smartsearch.sssearchserver.utility;

import arki.smartsearch.common.pojo.SofItem;
import arki.smartsearch.sssearchserver.conf.ESConfig;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class ESUtility {

    @Autowired
    @Qualifier("myRestHighLevelClient")
    private RestHighLevelClient client;

    private static final String INDEX = "se01";

    private static final Integer PAGE_SIZE = 20;

    public Map<String, Object> searchByKeyword(String keyword, int fromPage) throws IOException {
        Map<String, Object> map = new HashMap<>();
        ArrayList<SofItem> sofItems = new ArrayList<>();
        SearchRequest searchRequest = new SearchRequest(INDEX);
        // 构建搜索条件
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        sourceBuilder.size(PAGE_SIZE);
        sourceBuilder.from(fromPage);

        // 查询条件， 可以使用QueryBuilders工具类实现
        // QueryBuilders.termQuery 精确
        MultiMatchQueryBuilder matchQueryBuilder = new MultiMatchQueryBuilder(keyword);
        //weight
        matchQueryBuilder.field("title", 3);
        matchQueryBuilder.field("questionDesc", 1.5F);
        matchQueryBuilder.field("answer", 1);
        // 单字段查询
//        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("title", keyword);
        sourceBuilder.query(matchQueryBuilder);
         //QueryBuilders.matchQuery(); //匹配所有
//        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "kuangshen1");
        // MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
        sourceBuilder.query(matchQueryBuilder);
        sourceBuilder.timeout(new TimeValue(6, TimeUnit.SECONDS));

        searchRequest.source(sourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

//        SearchResponse searchResponse = this.restHighLevelClient().search(searchRequest, RequestOptions.DEFAULT);
        for (SearchHit documentFields : searchResponse.getHits().getHits()) {
            SofItem item = new SofItem();
            Map<String, Object> docitem = documentFields.getSourceAsMap();
            item.setDate((String) docitem.get("date"));
            item.setAnswers((ArrayList<String>) docitem.get("answers"));
            item.setFavicon((String) docitem.get("favicon"));
            item.setHostname((String) docitem.get("hostname"));
            item.setTitle((String) docitem.get("title"));
            item.setQuestionId((String) docitem.get("questionId"));
            item.setQuestionDesc((String) docitem.get("questionDesc"));
            sofItems.add(item);
        }

//        System.out.println(sofItems);
        map.put("resultItems", sofItems);
        map.put("totalNum", searchResponse.getHits().getTotalHits());
        return map;
    }

    public Map<String, Object> searchByKeyword_title(String keyword) throws IOException {
        Map<String, Object> map = new HashMap<>();
        ArrayList<SofItem> sofItems = new ArrayList<>();
        SearchRequest searchRequest = new SearchRequest(INDEX);
        // 构建搜索条件
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        sourceBuilder.size(6);
        sourceBuilder.from(0);
        // 单字段查询
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("title", keyword);
        sourceBuilder.query(matchQueryBuilder);
        sourceBuilder.timeout(new TimeValue(6, TimeUnit.SECONDS));
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
//        SearchResponse searchResponse = this.restHighLevelClient().search(searchRequest, RequestOptions.DEFAULT);
        for (SearchHit documentFields : searchResponse.getHits().getHits()) {
            SofItem item = new SofItem();
            Map<String, Object> docitem = documentFields.getSourceAsMap();
            item.setDate((String) docitem.get("date"));
            item.setAnswers((ArrayList<String>) docitem.get("answers"));
            item.setFavicon((String) docitem.get("favicon"));
            item.setHostname((String) docitem.get("hostname"));
            item.setTitle((String) docitem.get("title"));
            item.setQuestionId((String) docitem.get("questionId"));
            item.setQuestionDesc((String) docitem.get("questionDesc"));
            sofItems.add(item);
        }
//        System.out.println(sofItems);
        map.put("resultItems", sofItems);
        map.put("totalNum", searchResponse.getHits().getTotalHits());
        return map;
    }

}

