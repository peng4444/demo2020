package cn.pbj.demo2020.sso_vue.service;


import cn.pbj.demo2020.sso_vue.domain.CustomData;
import cn.pbj.demo2020.sso_vue.domain.ResultDetails;
import cn.pbj.demo2020.sso_vue.exception.CustomizeException;

import java.util.List;

/**
 * @author Pu Zhiwei {@literal puzhiweipuzhiwei@foxmail.com}
 * create          2019-12-01 16:22
 */
public interface SystemDataService {
    List<CustomData> get();

    CustomData select(String id) throws CustomizeException;

    ResultDetails delete(String id, String authorities) throws CustomizeException;

    CustomData create(CustomData customData);
}
