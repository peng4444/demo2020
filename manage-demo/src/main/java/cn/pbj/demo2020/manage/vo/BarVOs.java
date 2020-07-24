package cn.pbj.demo2020.manage.vo;

import java.util.List;

/**
 * @pClassName: BarVOs
 * @author: pengbingjiang
 * @create: 2020/7/24 15:19
 * @description: TODO
 */
public class BarVOs {
    private List<String> name;//用户名称
    private List<String> value;//用户订单总价

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    public List<String> getValue() {
        return value;
    }

    public void setValue(List<String> value) {
        this.value = value;
    }
}
