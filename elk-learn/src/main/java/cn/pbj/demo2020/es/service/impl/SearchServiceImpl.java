package cn.pbj.demo2020.es.service.impl;

import cn.pbj.demo2020.es.constants.Constants;
import cn.pbj.demo2020.es.entity.Student;
import cn.pbj.demo2020.es.service.SearchService;
import com.google.gson.Gson;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.ParsedAvg;
import org.elasticsearch.search.aggregations.metrics.ParsedSum;
import org.elasticsearch.search.aggregations.metrics.ParsedValueCount;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @pClassName: SearchServiceImpl
 * @author: pengbingjiang
 * @create: 2020/12/10 11:15
 * @description: TODO
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Resource
    private Gson gson;

    @Resource
    private RestHighLevelClient restHighLevelClient;
    
    /**
    * @Description: 区间搜索 区间搜索其实也是组合搜索的一个子条件，其他的搜索其实也都是
    * @Param: [from, to, field, index]
    * @return: java.util.List<cn.pbj.demo2020.es.entity.Student>
    * @Author: pengbingjiang
    * @Date: 2020/12/10 13:05
    */
    @Override
    public List<Student> searchRange(Object from, Object to, String field, String index) {
        List<Student> list = new ArrayList<>();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(field);
        if (from != null) {
            // 第一个参数就是字段的下边界，第二个参数代表是否包含边界。
            rangeQueryBuilder.from(from, true);
        }
        if (to != null) {
            rangeQueryBuilder.to(to, true);
        }
        boolQueryBuilder.must();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder);
        SearchRequest searchRequest = new SearchRequest(index);
        searchRequest.source(searchSourceBuilder);
        try {
            // 搜索的响应对象，所有的数据都在SearchHit对象中
            SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            for (SearchHit hit : search.getHits()) {
                String source = hit.getSourceAsString();
                Student student = gson.fromJson(source, Student.class);
                list.add(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Student> searchAgeRange(Integer from, Integer to, String index) {
        List<Student> list = new ArrayList<>();
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(QueryBuilders.rangeQuery("age").gte(from).lte(to));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQuery);
        SearchRequest searchRequest = new SearchRequest(index);
        searchRequest.source(searchSourceBuilder);
        try {
            SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            for (SearchHit hit : search.getHits()) {
                String source = hit.getSourceAsString();
                Student student = gson.fromJson(source, Student.class);
                list.add(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
    * @Description: 搜索年龄在18到19岁并且班级为'G0305'的学生
    * @Param: []
    * @return: java.util.List<cn.pbj.demo2020.es.entity.Student>
    * @Author: pengbingjiang
    * @Date: 2020/12/10 13:07
    */
    @Override
    public List<Student> searchBool() {
        List<Student> list = new ArrayList<>();
        // 类BoolQueryBuilder就是组合查询构建器，这个类可以用来构建组合的条件查询。
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        // boolQuery.must()方法就是用来拼接条件的一种方式，使用这个方法代表必须满足这个条件才会查询出来
        boolQuery.must(QueryBuilders.rangeQuery("age").gte(18).lte(19));
        boolQuery.must(QueryBuilders.termQuery("clazz" + Constants.KEYWORD,"G0305"));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQuery);
        SearchRequest searchRequest = new SearchRequest(Constants.INDEX_STUDENT);
        searchRequest.source(searchSourceBuilder);
        try {
            SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            for (SearchHit hit : search.getHits()) {
                String source = hit.getSourceAsString();
                Student student = gson.fromJson(source, Student.class);
                list.add(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void searchBoolAndAggregation() {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(QueryBuilders.rangeQuery("age").gte(18).lte(19));
        boolQuery.must(QueryBuilders.termQuery("clazz" + Constants.KEYWORD,"G0305"));
        // 聚合分组：按clazz字段分组，并将结果取名为clazz，es默认是分词的，为了精确配置，需要加上‘.keyword’关键词后缀。
        TermsAggregationBuilder aggregationBuilder = AggregationBuilders.terms("clazz").field("clazz" + Constants.KEYWORD);
        // 聚合求和：求符合查询条件的学生的年龄的和，并将结果取名为ageSum，因为不是字符串，所以默认是精确匹配，不支持分词。
        aggregationBuilder.subAggregation(AggregationBuilders.sum("ageSum").field("age"));
        // 聚合求平均：求符合查询条件的学生的年龄的平均值，并将结果取名为ageAvg，因为不是字符串，所以默认是精确匹配，不支持分词。
        aggregationBuilder.subAggregation(AggregationBuilders.avg("ageAvg").field("age"));
        // 聚合求数量：按学号查询符合查询条件的学生个数，并将结果取名为count，es默认是分词的，为了精确配置，需要加上‘.keyword’关键词后缀。
        aggregationBuilder.subAggregation(AggregationBuilders.count("count").field("studentNo" + Constants.KEYWORD));
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(boolQuery);
        builder.aggregation(aggregationBuilder);
        // 按年龄降序排序。
        builder.sort("age", SortOrder.DESC);
        SearchRequest request = new SearchRequest("student_info");
        request.source(builder);
        try {
            SearchResponse search = restHighLevelClient.search(request, RequestOptions.DEFAULT);
            for (SearchHit hit : search.getHits()) {
                String source = hit.getSourceAsString();
                Student student = gson.fromJson(source, Student.class);
                System.out.println(student);
            }
            // 使用Terms对象接收
            Terms clazz = search.getAggregations().get("clazz");
            for (Terms.Bucket bucket : clazz.getBuckets()) {
                System.out.println(bucket.getDocCount());

                System.out.println("=====================");
                // 使用ParsedSum对象接收
                ParsedSum ageCount = bucket.getAggregations().get("ageSum");
                System.out.println(ageCount.getType());
                System.out.println(ageCount.getValue());
                System.out.println(ageCount.getValueAsString());
                System.out.println(ageCount.getMetaData());
                System.out.println(ageCount.getName());

                System.out.println("=====================");
                // 使用ParsedAvg对象接收
                ParsedAvg ageAvg = bucket.getAggregations().get("ageAvg");
                System.out.println(ageAvg.getType());
                System.out.println(ageAvg.getValue());
                System.out.println(ageAvg.getValueAsString());
                System.out.println(ageAvg.getMetaData());
                System.out.println(ageAvg.getName());

                System.out.println("=====================");
                // 使用ParsedValueCount对象接收
                ParsedValueCount count = bucket.getAggregations().get("count");
                System.out.println(count.getType());
                System.out.println(count.getValue());
                System.out.println(count.getValueAsString());
                System.out.println(count.getMetaData());
                System.out.println(count.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
    * @Description: 分词查询，分词查询就不加.keyword关键字即可。一般的进行分词都是字符串才进行分词搜索，数字等类型只能是精准匹配。
    * @Param: [matchStudentName]
    * @return: java.util.List<cn.pbj.demo2020.es.entity.Student>
    * @Author: pengbingjiang
    * @Date: 2020/12/10 13:10
    */
    @Override
    public List<Student> searchMatch(String matchStudentName) {
        List<Student> list = new ArrayList<>();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        // 分词查询时不加'.keyword'关键字
        boolQueryBuilder.must(QueryBuilders.matchQuery("studentName",matchStudentName));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder);
        SearchRequest searchRequest = new SearchRequest("student_info");
        searchRequest.source(searchSourceBuilder);
        try {
            SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            for (SearchHit hit : search.getHits().getHits()) {
                String source = hit.getSourceAsString();
                Student student = gson.fromJson(source, Student.class);
                list.add(student);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
