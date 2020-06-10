package cn.pbj.demo2020.es.service.impl;

import cn.pbj.demo2020.es.constants.Constants;
import cn.pbj.demo2020.es.entity.Student;
import cn.pbj.demo2020.es.service.StudentService;
import com.google.gson.Gson;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.GetSourceRequest;
import org.elasticsearch.client.core.GetSourceResponse;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.xcontent.XContentType;
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
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: StudentServiceImpl
 * @Author: pbj
 * @Date: 2020/6/10 08:44
 * @Description: TODO
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    private Gson gson;

    @Resource
    private RestHighLevelClient restHighLevelClient;

    @Resource
    private StudentService studentService;

    @Override
    public String index(Student student) {
        StringBuilder builder = new StringBuilder();
        IndexRequest indexRequest = this.initIndexRequest(student);
        try {
            // 同步索引到elasticsearch服务器，获取索引响应IndexResponse
            IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
            String statusName = indexResponse.status().name();
            int statusCode = indexResponse.status().getStatus();
            builder.append(statusName).append(Constants.CONNECTOR).append(statusCode);
        } catch (IOException e) {
            builder.append("Fail").append(Constants.CONNECTOR).append(e.getMessage());
        }
        return builder.toString();
    }

    @Override
    public String indexAsync(Student student) {
        StringBuilder builder = new StringBuilder();
        IndexRequest indexRequest = this.initIndexRequest(student);
        // 异步索引到elasticsearch服务器，获取索引响应IndexResponse
        restHighLevelClient.indexAsync(indexRequest, RequestOptions.DEFAULT,actionListener(builder));
        return builder.toString();
    }

    /**
     * 初始化IndexRequest，并设置数据源。
     * @param student
     * @return IndexRequest
     */
    private IndexRequest initIndexRequest(Student student) {
        // 构建IndexRequest，设置索引名称，索引类型，索引id
        IndexRequest indexRequest = new IndexRequest(Constants.INDEX_STUDENT);
        // 可以不设置，默认就是'_doc'
        indexRequest.type(Constants.DOC_TYPE);
        // 设置索引id为studentId
        indexRequest.id(String.valueOf(student.getId()));
        // 设置数据源
        String studentJson = gson.toJson(student);
        indexRequest.source(studentJson, XContentType.JSON);
        return indexRequest;
    }

    /**
     * 异步索引的回调监听器，根据不同的结果做出不同的处理
     * @param builder
     * @return ActionListener<IndexResponse>
     */
    private ActionListener<IndexResponse> actionListener(StringBuilder builder) {
        return new ActionListener<IndexResponse>() {
            // 当索引数据到es服务器时，返回不同的状态
            @Override
            public void onResponse(IndexResponse indexResponse) {
                String statusName = indexResponse.status().name();
                int statusCode = indexResponse.status().getStatus();
                builder.append(statusName).append(Constants.CONNECTOR).append(statusCode);
            }

            // 当索引数据时出现异常
            @Override
            public void onFailure(Exception e) {
                builder.append("Fail").append(Constants.CONNECTOR).append(e.getMessage());
            }
        };
    }

    @Override
    public List<Student> searchRange(Object from, Object to, String field, String index) {
        List<Student> list = new ArrayList<>();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(field);
        if (from != null) {
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


    @Override
    public List<Student> searchBool() {
        List<Student> list = new ArrayList<>();
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
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

    @Override
    public Student get(String id) {
        Student student = new Student();
        GetRequest getRequest = new GetRequest(Constants.INDEX_STUDENT, id);
        try {
            GetResponse getResponse = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
            String source = getResponse.getSourceAsString();
            student = gson.fromJson(source, Student.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public Student getAsync(String id) {
        Student student = new Student();
        // 设置索引和文档id
        GetRequest getRequest = new GetRequest(Constants.INDEX_STUDENT, id);
        restHighLevelClient.getAsync(getRequest, RequestOptions.DEFAULT, getActionListener(student));
        return student;
    }

    @Override
    public Student getSource(String id) {
        Student student = new Student();
        GetSourceRequest getSourceRequest = this.initGetSourceRequest(id);
        try {
            GetSourceResponse source = restHighLevelClient.getSource(getSourceRequest, RequestOptions.DEFAULT);
            Map<String, Object> objectMap = source.getSource();
            student.setId((Long) objectMap.get("id"));
            // 只有studentName字段会有值
            student.setStudentName((String) objectMap.get("studentName"));
            student.setStudentNo((String) objectMap.get("studentNo"));
            student.setSex((String) objectMap.get("sex"));
            student.setAge((Integer) objectMap.get("age"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public Student getSourceAsync(String id) {
        Student student = new Student();
        GetSourceRequest getSourceRequest = this.initGetSourceRequest(id);
        restHighLevelClient.getSourceAsync(getSourceRequest, RequestOptions.DEFAULT, getAsyncActionListener(student));
        return student;
    }

    @Override
    public Boolean exists(String id) {
        Boolean bool = false;
        GetRequest getRequest = new GetRequest(Constants.INDEX_STUDENT, id);
        try {
            bool = restHighLevelClient.exists(getRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bool;
    }

    /**
     * 初始化GetSourceRequest，并设置数据源。
     * @param id
     * @return GetSourceRequest
     */
    private GetSourceRequest initGetSourceRequest(String id) {
        // 构建GetSourceRequest
        GetSourceRequest getSourceRequest = new GetSourceRequest(Constants.INDEX_STUDENT, id);
        // 设置includes为studentName为搜索字段，只搜索studentName这个字段，比如：select * from table 变成 select table.studentName from table
        String[] includes = new String[]{"studentName"};
        // 设置excludes为空
        String[] excludes = Strings.EMPTY_ARRAY;
        // 设置拿取数据源上下文的包含和排除的字段，includes和excludes都为null就是获取一整个对象
        FetchSourceContext context = new FetchSourceContext(true, includes, excludes);
        getSourceRequest.fetchSourceContext(context);
        return getSourceRequest;
    }

    /**
     * 异步获取的回调监听器，根据不同的结果做出不同的处理
     * @param student
     * @return ActionListener<GetResponse>
     */
    private ActionListener<GetResponse> getActionListener(Student student) {
        return new ActionListener<GetResponse>() {
            @Override
            public void onResponse(GetResponse getResponse) {
                String source = getResponse.getSourceAsString();
                Student JSONStudent = gson.fromJson(source, Student.class);
                student.setId(JSONStudent.getId());
                student.setStudentNo(JSONStudent.getStudentNo());
                student.setStudentName(JSONStudent.getStudentName());
                student.setSex(JSONStudent.getSex());
                student.setAge(JSONStudent.getAge());
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
            }
        };
    }

    /**
     * 异步获取数据源的回调监听器，根据不同的结果做出不同的处理
     * @param student
     * @return ActionListener<GetSourceResponse>
     */
    private ActionListener<GetSourceResponse> getAsyncActionListener(Student student) {
        return new ActionListener<GetSourceResponse>() {
            @Override
            public void onResponse(GetSourceResponse getSourceResponse) {
                Map<String, Object> objectMap = getSourceResponse.getSource();
                student.setId((Long) objectMap.get("id"));
                // 只有studentName字段会有值
                student.setStudentName((String) objectMap.get("studentName"));
                student.setStudentNo((String) objectMap.get("studentNo"));
                student.setSex((String) objectMap.get("sex"));
                student.setAge((Integer) objectMap.get("age"));
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
            }
        };
    }

    @Override
    public String update(Student student) {
        this.checkData(String.valueOf(student.getId()));
        UpdateRequest updateRequest = new UpdateRequest(Constants.INDEX_STUDENT, String.valueOf(student.getId()));
        String source = gson.toJson(student);
        updateRequest.doc(source, XContentType.JSON);
        try {
            restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "修改成功！";
    }

    @Override
    public String updateAsync(Student student) {
        this.checkData(String.valueOf(student.getId()));
        StringBuilder builder = new StringBuilder();
        UpdateRequest updateRequest = new UpdateRequest(Constants.INDEX_STUDENT, String.valueOf(student.getId()));
        String source = gson.toJson(student);
        updateRequest.doc(source, XContentType.JSON);
        restHighLevelClient.updateAsync(updateRequest, RequestOptions.DEFAULT, updateActionListener(builder));
        return builder.toString();
    }

    /**
     * 异步更新的回调监听器，根据不同的结果做出不同的处理
     * @param builder
     * @return ActionListener<UpdateResponse>
     */
    private ActionListener<UpdateResponse> updateActionListener(StringBuilder builder) {
        return new ActionListener<UpdateResponse>() {
            @Override
            public void onResponse(UpdateResponse updateResponse) {
                String statusName = updateResponse.status().name();
                int statusCode = updateResponse.status().getStatus();
                builder.append(statusName).append(Constants.CONNECTOR).append(statusCode);
            }

            @Override
            public void onFailure(Exception e) {
                builder.append("Fail").append(Constants.CONNECTOR).append(e.getMessage());
            }
        };
    }

    @Override
    public String delete(String id) {
        this.checkData(id);
        DeleteRequest deleteRequest = new DeleteRequest(Constants.INDEX_STUDENT, id);
        try {
            restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            return "删除失败！";
        }
        return "删除成功！";
    }

    @Override
    public String deleteAsync(String id) {
        this.checkData(id);
        StringBuilder builder = new StringBuilder();
        DeleteRequest deleteRequest = new DeleteRequest(Constants.INDEX_STUDENT, id);
        restHighLevelClient.deleteAsync(deleteRequest, RequestOptions.DEFAULT, deleteActionListener(builder));
        return builder.toString();
    }

    /**
     * 核查数据
     * @param id
     */
    private void checkData(String id) {
        if (id == null) {
            throw new RuntimeException("参数student的id不能为null。");
        }
        if (Boolean.FALSE.equals(studentService.exists(id))) {
            throw new RuntimeException(String.format("不存在id为%s的文档。", id));
        }
    }

    /**
     * 异步删除的回调监听器，根据不同的结果做出不同的处理
     * @param builder
     * @return ActionListener<DeleteResponse>
     */
    private ActionListener<DeleteResponse> deleteActionListener(StringBuilder builder) {
        return new ActionListener<DeleteResponse>() {
            @Override
            public void onResponse(DeleteResponse deleteResponse) {
                String statusName = deleteResponse.status().name();
                int statusCode = deleteResponse.status().getStatus();
                builder.append(statusName).append(Constants.CONNECTOR).append(statusCode);
            }

            @Override
            public void onFailure(Exception e) {
                builder.append("Fail").append(Constants.CONNECTOR).append(e.getMessage());
            }
        };
    }
}
