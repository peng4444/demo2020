package cn.pbj.demo2020.es.service.impl;

import cn.pbj.demo2020.es.constants.Constants;
import cn.pbj.demo2020.es.entity.Student;
import cn.pbj.demo2020.es.service.IndexService;
import com.google.gson.Gson;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @pClassName: IndexServiceImpl
 * @author: pengbingjiang
 * @create: 2020/12/10 11:06
 * @description: TODO
 */
@Service
public class IndexServiceImpl implements IndexService {

    @Resource
    private Gson gson;

    @Resource
    private RestHighLevelClient restHighLevelClient;

    @Override
    public String index(Student student) {
        StringBuilder builder = new StringBuilder();
        IndexRequest indexRequest = this.initIndexRequest(student);
        try {
            // 同步索引到elasticsearch服务器，获取索引响应IndexResponse
            IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
            String statusName = indexResponse.status().name();
            int statusCode = indexResponse.status().getStatus();
            // 添加成功 返回结果以statusName开头
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
}
