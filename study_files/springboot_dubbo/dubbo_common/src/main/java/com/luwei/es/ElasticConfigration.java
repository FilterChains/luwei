package com.luwei.es;//package com.luwei.es;
//
//import org.apache.http.HttpHost;
//import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
//import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
//import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
//import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
//import org.elasticsearch.action.delete.DeleteResponse;
//import org.elasticsearch.action.get.GetRequest;
//import org.elasticsearch.action.get.GetResponse;
//import org.elasticsearch.action.index.IndexRequest;
//import org.elasticsearch.action.index.IndexResponse;
//import org.elasticsearch.action.search.SearchRequest;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.action.support.master.AcknowledgedResponse;
//import org.elasticsearch.action.update.UpdateRequest;
//import org.elasticsearch.action.update.UpdateResponse;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.xcontent.XContentType;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.search.builder.SearchSourceBuilder;
//import org.springframework.context.annotation.Configuration;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
///***
// ** 配置ES,支持集群
// */
//@Configuration
//public class ElasticConfigration {
//
//    public static void main(String[] args) {
//        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
//                RestClient.builder(new HttpHost("192.168.3.111", 9200, "http")));
//        System.out.println("链接状态:" + restHighLevelClient);
//        try {
//            restHighLevelClient.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * <p>@description : 创建索引 </p>
//     * <p>@author : Wei.Lu</p>
//     * <p>@date : 2020/5/13 17:24 </p>
//     */
//    public void createIndex(RestHighLevelClient client) {
//        //索引名称
//        CreateIndexRequest request = new CreateIndexRequest("hello");
//        //分片副本
//        request.settings(Settings.builder().put("index.number_of_shards", 5)
//                .put("index.number_of_replicas", 1));
//        //内容
//        Map<String, Object> id = new HashMap<>();
//        id.put("type", "text");
//        id.put("store", true);
//        Map<String, Object> title = new HashMap<>();
//        title.put("type", "text");
//        title.put("store", true);
//        title.put("index", true);
//        title.put("analyzer", "standard");
//        Map<String, Object> properties = new HashMap<>();
//        properties.put("id", id);
//        properties.put("title", title);
//        Map<String, Object> mapping = new HashMap<>();
//        mapping.put("properties", properties);
//        request.mapping(String.valueOf(mapping));
//        try {
//            CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
//            System.out.println(response.toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * <p>@description : 查询指定索引 </p>
//     * <p>@author : Wei.Lu</p>
//     * <p>@date : 2020/5/13 17:25 </p>
//     */
//    public void getIndex(RestHighLevelClient client) throws IOException {
//        //索引名称
//        GetIndexRequest request = new GetIndexRequest("hello");
//        try {
//            boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
//            System.out.println(exists);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * <p>@description : 删除索引</p>
//     * <p>@author : Wei.Lu</p>
//     * <p>@date : 2020/5/13 17:25 </p>
//     */
//    public void delIndex(RestHighLevelClient client) {
//        DeleteIndexRequest request = new DeleteIndexRequest("hello");
//        try {
//            AcknowledgedResponse delete = client.indices().delete(request, RequestOptions.DEFAULT);
//            System.out.println(delete.toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * <p>@description : 创建文档 </p>
//     * <p>@author : Wei.Lu</p>
//     * <p>@date : 2020/5/13 17:26 </p>
//     */
//    public void createDocument(RestHighLevelClient client) {
//        //索引名称
//        IndexRequest indexRequest = new IndexRequest("hello");
//        ObjectMapper mapper = new ObjectMapper();
//        Article article = new Article(3L, "web前端");
//        byte[] json = new byte[0];
//        try {
//            json = mapper.writeValueAsBytes(article);
//            //可以设置文章ID
//            indexRequest.id("5");
//            indexRequest.source(json, XContentType.JSON);
//            IndexResponse index = client.index(indexRequest, RequestOptions.DEFAULT);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * <p>@description : 根据文档ID查询文档  </p>
//     * <p>@author : Wei.Lu</p>
//     * <p>@date : 2020/5/13 17:26 </p>
//     */
//    public void getDocument(RestHighLevelClient client) {
//        GetRequest getRequest = new GetRequest("hello", "1");
//        GetResponse documentFields = null;
//        try {
//            documentFields = client.get(getRequest, RequestOptions.DEFAULT);
//            System.out.println(documentFields.toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * <p>@description : 更新文档 </p>
//     * <p>@author : Wei.Lu</p>
//     * <p>@date : 2020/5/13 17:26 </p>
//     */
//    public void updateDocument(RestHighLevelClient client) {
//        UpdateRequest updateRequest = new UpdateRequest("hello", "1");
//        Article article = new Article(2L, "java入门到放弃");
//        ObjectMapper mapper = new ObjectMapper();
//        byte[] json = new byte[0];
//        try {
//            json = mapper.writeValueAsBytes(article);
//            IndexRequest indexRequest = new IndexRequest("hello");
//            indexRequest.source(json, XContentType.JSON);
//            updateRequest.doc(indexRequest);
//            UpdateResponse updateResponse = client.update(
//                    updateRequest, RequestOptions.DEFAULT);
//            System.out.println(updateResponse);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * <p>@description : 删除文档 </p>
//     * <p>@author : Wei.Lu</p>
//     * <p>@date : 2020/5/13 17:27 </p>
//     */
//    public void delDocument(RestHighLevelClient client) {
//        DeleteRequest request = new DeleteRequest("hello", "1");
//        try {
//            DeleteResponse deleteResponse = client.delete(
//                    request, RequestOptions.DEFAULT);
//            System.out.println(deleteResponse);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * <p>@description : 查询文档 </p>
//     * <p>@author : Wei.Lu</p>
//     * <p>@date : 2020/5/13 17:27 </p>
//     */
//    public void searchDocument(RestHighLevelClient client) {
//        SearchRequest searchRequest = new SearchRequest();
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
//        searchRequest.source(searchSourceBuilder);
//        SearchResponse searchResponse = null;
//        try {
//            searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
//            System.out.println(searchResponse.toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//}
