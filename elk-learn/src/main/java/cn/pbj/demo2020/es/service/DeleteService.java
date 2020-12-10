package cn.pbj.demo2020.es.service;

/**
 * @pClassName: DeleteService
 * @author: pengbingjiang
 * @create: 2020/12/10 11:13
 * @description: TODO
 */
public interface DeleteService {

    /**
     * 同步删除一个文档
     * @param id：文档id
     * @return String
     */
    public String delete(String id);


    /**
     * 异步删除一个文档
     * @param id：文档id
     * @return String
     */
    public String deleteAsync(String id);

}
