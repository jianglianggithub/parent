package com;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.max.InternalMax;
import org.elasticsearch.search.aggregations.metrics.max.MaxAggregationBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest

public class TcpClientTest {

    @Autowired
    TransportClient client;


    String mapping="{\n" +
            "\t\"properties\": {\n" +
            "                \"commodity_id\": {\n" +
            "                    \"type\": \"long\"\n" +
            "                },\n" +
            "                \"commodity_name\": {\n" +
            "                    \"type\": \"text\",\n" +
            "                    \"analyzer\": \"ik_smart\"\n" +
            "                },\n" +
            "                \"picture_url\": {\n" +
            "                    \"type\": \"keyword\"\n" +
            "                },\n" +
            "                \"price\": {\n" +
            "                    \"type\": \"double\"\n" +
            "                }\n" +
            "            }\n" +
            "            \n" +
            "\t\n" +
            "}";

    @Test
    public void zxc(){
        System.out.println(mapping);
    }

    @Test
    public void test1() throws IOException {

        CreateIndexRequest mapping = new CreateIndexRequest().index("aa").mapping("doc", this.mapping, XContentType.JSON);
        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("number_of_shards","1");
        objectObjectHashMap.put("number_of_replicas","1");
        mapping.settings(objectObjectHashMap);
        CreateIndexResponse createIndexResponse = client.admin().indices().create(mapping).actionGet();
        System.out.println(createIndexResponse.isAcknowledged());
    }

    // 批量添加数据  bulk 可以一次性执行多条 增删改的操作。
    @Test
    public void test2(){
        BulkRequestBuilder bulk = client.prepareBulk();

        for (int i = 50; i < 60; i++) {
            Item item = new Item();
            item.commodity_id=new Long(i);
            item.price=1.1;
            item.commodity_name="一条河";
            item.picture_url="http://wwww.baidu.c"+i;
            String string = JSON.toJSONString(item);
            Map map=JSON.parseObject(string,Map.class);
            bulk.add(client.prepareIndex("aa","doc",i+"").setSource(string,XContentType.JSON));// 这个默认就是json其实不用加
        }

        BulkResponse bulkItemResponses = bulk.get();

        System.out.println(bulkItemResponses);
    }


    // 删除指定文档
    @Test
    public void test3() {
        DeleteResponse deleteResponse = client.prepareDelete("aa", "doc", "3").get();
        RestStatus status = deleteResponse.status();
        System.out.println();
    }

    //修改
    @Test
    public void test4(){
        GetResponse documentFields = client.prepareGet("aa", "doc", "2").execute().actionGet();

        Map<String, Object> source = documentFields.getSource();

        source.put("picture_url","特写+++");
        System.out.println(source);
        UpdateResponse updateResponse = client.prepareUpdate("aa", "doc", "2").setDoc(source,XContentType.JSON).execute().actionGet();
        System.out.println(updateResponse.status().getStatus()==200);

    }


    //搜素   prepare 开头的 api 和 不带 prepare 的api 差别就在于是否用  建造者的设计模式 来构建。
    // 其实在 prepareSearch 就new SearchRequest  相当于 rest client的第一步
    //  然后  setQuery 的时候 new SearchQueryBuilder 相当于  SearchRequest.Source 那一步
    // 其实是 差不多的。。 rest client  和 基于tcp 长连接的客户端 其实是差不多的 而且  还 适配了 rest风格的api
    @Test
    public void test5(){
        SearchResponse searchResponse = client.prepareSearch("aa").setTypes("doc")
                .setQuery(QueryBuilders.termQuery("commodity_name", "我")).get();// 对搜索的关键字不分词

        for (SearchHit hit : searchResponse.getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
    }

    //
    @Test
    public void test6(){
        SearchResponse searchResponse = client.prepareSearch("aa").setTypes("doc")
                .setQuery(
                        QueryBuilders.matchQuery("commodity_name", "你妈无了")
                       // .minimumShouldMatch("50%") 代表 某个文档是否 能够返回 原始字段 必须要对应的field 中满足分词后的 1/2 个词 才能匹配
                ).get();

        for (SearchHit hit : searchResponse.getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
    }


    public void test7(){
        SearchResponse searchResponse = client.prepareSearch("aa").setTypes("doc")
                .setQuery(
                        QueryBuilders.multiMatchQuery("我干你妈", "filed1","filed2")
                        .field("filed1",2)  // 设置得分 那么满足对应的filed的 文档会排的越前面
                         .minimumShouldMatch("50%") //代表 某个文档是否 能够返回 原始字段 必须要对应的field 中满足分词后的 1/2 个词 才能匹配
                ).get();

        for (SearchHit hit : searchResponse.getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
    }



    public void test8(){
        SearchResponse searchResponse = client.prepareSearch("aa").setTypes("doc")
                .setQuery(
                        QueryBuilders.rangeQuery( "filed1").gt(1).lt(2)
                ).get();

        for (SearchHit hit : searchResponse.getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
    }

    @Test //通配符模式
    public void test9(){
        SearchResponse searchResponse = client.prepareSearch("aa").setTypes("doc")
                .setQuery(
                        QueryBuilders.wildcardQuery( "commodity_name","这*")
                ).get();

        for (SearchHit hit : searchResponse.getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
    }



    @Test
    public void test10(){
        SearchResponse searchResponse = client.prepareSearch("aa").setTypes("doc")
                .setQuery(
                        QueryBuilders.boolQuery()
                                .should(QueryBuilders.termQuery("commodity_id",5))
                                .should(QueryBuilders.rangeQuery("commodity_id").gte(6).lte(8))
                ).get();

        for (SearchHit hit : searchResponse.getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
    }

    @Test
    public void test11(){
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("commodity_name");
        highlightBuilder.preTags("++++");
        highlightBuilder.postTags("++++");
        SearchResponse searchResponse = client.prepareSearch("aa").setTypes("doc")
                .setQuery(
                        QueryBuilders.matchQuery("commodity_name","一条河")
                ).highlighter(highlightBuilder).get();
        for (SearchHit hit : searchResponse.getHits()) {
            Map<String, Object> source = hit.getSourceAsMap();
            // 一个doc 有可能 多个feild 都添加了高亮
            Map<String, HighlightField> higSource = hit.getHighlightFields();
            for (Map.Entry<String, HighlightField> entry : higSource.entrySet()) {
                String field = entry.getKey();
                source.put(field,this.pingjieString(entry.getValue().getFragments()));
            }
            System.out.println(source);
        }
    }

    private Object pingjieString(Text[] fragments) {
        String temp="";
        for (int i = 0; i < fragments.length; i++) {
            temp+=fragments[i];
        }
        return temp;
    }

    // 分组聚合计数   分组可以和 mysql一样 多重分组  group by  xx,xx,xx
    @Test
    public void test14(){
        TermsAggregationBuilder r1 = AggregationBuilders.terms("r1").field("team");
        TermsAggregationBuilder r2 = AggregationBuilders.terms("r2").field("position");
        r1.subAggregation(r2);// 关联起来
        SearchResponse searchResponse = client.prepareSearch("test2").setTypes("doc").setQuery(
                QueryBuilders.matchAllQuery()
        ).addAggregation(r1).get();
        Aggregations aggregations = searchResponse.getAggregations();
        for (Aggregation aggregation : aggregations) {
            // System.out.println(aggregation.toString());
            StringTerms stringTerms = (StringTerms) aggregation;
            List<StringTerms.Bucket> buckets = stringTerms.getBuckets();
            for (StringTerms.Bucket bucket : buckets) {
                long docCount = bucket.getDocCount();
                Object key = bucket.getKey();
                System.out.println("当前队伍名称为" +  key + "该队伍下有"+docCount + "个球员");

                Aggregation posititon_count = bucket.getAggregations().get("r2");
                if(null != posititon_count){
                    StringTerms positionTrem = (StringTerms) posititon_count;
                    List<StringTerms.Bucket> buckets1 = positionTrem.getBuckets();
                    for (StringTerms.Bucket bucket1 : buckets1) {
                        Object key1 = bucket1.getKey();
                        long docCount1 = bucket1.getDocCount();
                        System.out.println("该队伍下面的位置为" +  key1+"该位置下有" +  docCount1 +"人");
                    }
                }
            }
            System.out.println("           ");
        }

    }

    //分组 max()
    @Test
    public void test15(){
        TermsAggregationBuilder r1 = AggregationBuilders.terms("r1").field("team");
        MaxAggregationBuilder r2 = AggregationBuilders.max("r2").field("age");
        r1.subAggregation(r2);

        SearchResponse searchResponse = client.prepareSearch("test2").setTypes("doc").setQuery(
                QueryBuilders.matchAllQuery()
        ).addAggregation(r1).get();


            List<StringTerms.Bucket> buckets = ((StringTerms) searchResponse.getAggregations().get("r1")).getBuckets();
            for (StringTerms.Bucket bucket : buckets) {
                Object key = bucket.getKey();
                InternalMax r21 = bucket.getAggregations().get("r2");
                System.out.println(key +" " + r21.getValue());
            }



    }




    @Test
    public void add() throws IOException {
        //批量添加数据开始

        BulkRequestBuilder bulkRequest = client.prepareBulk();

// either use client#prepare, or use Requests# to directly build index/delete requests
        bulkRequest.add(client.prepareIndex("test2", "doc", "10")
                .setSource(jsonBuilder()
                        .startObject()
                        .field("name", "郭德纲")
                        .field("age", 33)
                        .field("salary", 3000)
                        .field("team", "cav")
                        .field("position", "sf")
                        .endObject()
                )
        );
        bulkRequest.add(client.prepareIndex("test2", "doc", "2")
                .setSource(jsonBuilder()
                        .startObject()
                        .field("name", "于谦")
                        .field("age", 25)
                        .field("salary", 2000)
                        .field("team", "cav")
                        .field("position", "pg")
                        .endObject()
                )
        );
        bulkRequest.add(client.prepareIndex("test2", "doc", "3")
                .setSource(jsonBuilder()
                        .startObject()
                        .field("name", "岳云鹏")
                        .field("age", 29)
                        .field("salary", 1000)
                        .field("team", "war")
                        .field("position", "pg")
                        .endObject()
                )
        );

        bulkRequest.add(client.prepareIndex("test2", "doc", "4")
                .setSource(jsonBuilder()
                        .startObject()
                        .field("name", "爱因斯坦")
                        .field("age", 21)
                        .field("salary", 300)
                        .field("team", "tim")
                        .field("position", "sg")
                        .endObject()
                )
        );

        bulkRequest.add(client.prepareIndex("test2", "doc", "5")
                .setSource(jsonBuilder()
                        .startObject()
                        .field("name", "张云雷")
                        .field("age", 26)
                        .field("salary", 2000)
                        .field("team", "war")
                        .field("position", "pf")
                        .endObject()
                )
        );
        bulkRequest.add(client.prepareIndex("test2", "doc", "6")
                .setSource(jsonBuilder()
                        .startObject()
                        .field("name", "爱迪生")
                        .field("age", 40)
                        .field("salary", 1000)
                        .field("team", "tim")
                        .field("position", "pf")
                        .endObject()
                )
        );
        bulkRequest.add(client.prepareIndex("test2", "doc", "7")
                .setSource(jsonBuilder()
                        .startObject()
                        .field("name", "牛顿")
                        .field("age", 21)
                        .field("salary", 500)
                        .field("team", "tim")
                        .field("position", "c")
                        .endObject()
                )
        );

        bulkRequest.add(client.prepareIndex("test2", "doc", "8")
                .setSource(jsonBuilder()
                        .startObject()
                        .field("name", "特斯拉")
                        .field("age", 20)
                        .field("salary", 500)
                        .field("team", "tim")
                        .field("position", "sf")
                        .endObject()
                )
        );
        bulkRequest.get();
    }
}
