package cn.pbj.demo2020.es.constants;

/**
 * @ClassName: Constants
 * @Author: pbj
 * @Date: 2020/6/10 08:31
 * @Description: TODO 常量类，放一些ES使用的常量，直接写字符串也行
 */
public class Constants {

    /**
     * es搜索关键字
     */
    public static final String KEYWORD = ".keyword";

    /**
     * es的type类型：type字段将在 elasticsearch-version：8 中彻底删除，本来就觉得没得啥用。
     */
    public static final String DOC_TYPE = "_doc";

    /**
     * 学生信息索引类型
     */
    public static final String INDEX_STUDENT = "student_info";


    /**
     * 自定连接符
     */
    public static final String CONNECTOR = " --> ";

}
