package com.yp.mall.search;

import com.alibaba.fastjson.JSON;
import com.yp.mall.search.bean.Account;
import com.yp.mall.search.bean.User;
import com.yp.mall.search.config.ElasticSearchConfig;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.Avg;
import org.elasticsearch.search.aggregations.metrics.AvgAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class GuliSearchApplicationTests {

    @Autowired
    private RestHighLevelClient client;

    @Test
    public void contextLoads() {
        System.out.println(client);
    }

    /**
     * 检索数据
     */
    @Test
    public void searchDataTest() throws IOException {
        SearchRequest searchRequest = new SearchRequest();

        // 指定索引
        searchRequest.indices("bank");

        // 1.指定检索条件 DSL
        SearchSourceBuilder builder = new SearchSourceBuilder();
        // 1.1 构造检索条件
        builder.query(QueryBuilders.matchQuery("address", "mill"));

        // 1.2 按照年龄值聚合
        TermsAggregationBuilder ageAgg = AggregationBuilders.terms("ageAgg").field("age").size(10);
        // 1.3 计算平均薪资
        AvgAggregationBuilder balanceA = AggregationBuilders.avg("balanceAvg").field("balance");
        ageAgg.subAggregation(balanceA);
        builder.aggregation(ageAgg);
        // 1.3 计算平均薪资
        AvgAggregationBuilder balanceAvg = AggregationBuilders.avg("balanceAvg").field("balance");
        builder.aggregation(balanceAvg);

        System.out.println("检索条件：" + builder.toString());
        searchRequest.source(builder);

        // 2.执行检索
        SearchResponse search = client.search(searchRequest, ElasticSearchConfig.COMMON_OPTIONS);

        // 3.分析结果
//		Map map = JSON.parseObject(search.toString(), Map.class);
//		System.out.println(map);
        // 3.1 索取所有记录
        SearchHits hits = search.getHits();
        // 详细记录
        SearchHit[] searchHits = hits.getHits();
        for (SearchHit hit : searchHits) {
//			String index = hit.getIndex();
//			String id = hit.getId();
            String source = hit.getSourceAsString();
            Account account = JSON.parseObject(source, Account.class);
            System.out.println(account);
        }
        // 获取分析数据
        Aggregations aggregations = search.getAggregations();
//		List<Aggregation> list = aggregations.asList();
//		for (Aggregation aggregation : list) {
//			Terms agg = aggregations.get(aggregation.getName());
//			System.out.println(agg.getBuckets());
//		}
        Terms agg = aggregations.get("ageAgg");
        for (Terms.Bucket bucket : agg.getBuckets()) {
            System.out.println("年龄: " + bucket.getKeyAsString() + "-->" + bucket.getDocCount() + "人");
        }

        Avg avg = aggregations.get("balanceAvg");
        System.out.println("平均薪资： " + avg.getValue());
    }

    /**
     * 插入数据
     */
    @Test
    public void indexTest() throws IOException {
        IndexRequest request = new IndexRequest("users");
        // 设置索引id
        request.id("1");
        // 第1种方式
//		request.source("userName","firenay","age","20","gender","男");

        // 第2种方式
        User user = new User();
        user.setUserName("yp");
        user.setAge("20");
        user.setGender("男");
        String jsonString = JSON.toJSONString(user);
        // 传入json时 指定类型
        request.source(jsonString, XContentType.JSON);

        // 执行保存操作
        IndexResponse response = client.index(request, ElasticSearchConfig.COMMON_OPTIONS);
        // 异步执行
//        client.indexAsync();

        System.out.println(response);
        System.out.println(response.status());
    }
}
