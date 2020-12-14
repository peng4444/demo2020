package cn.pbj.demo2020.ssm.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @pClassName: SysCodeEntity
 * @author: pengbingjiang
 * @create: 2020/12/14 10:21
 * @description: TODO 数字字典
 */
@Data
public class SysCode implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;

    // 分类编码
    private String kindCode;

    // 分类名称
    private String kindName;

    // CODE编码
    private String code;

    // CODE名称
    private String name;

    // 父级分类编码
    private String parentCode;

    // 排序
    private String disPlaySort;

    // 删除标记(0-未处理，1-已删除)
    private String deleteFlag;

    // 创建日期
    private Date createTime;

    // 创建人
    private String createUserId;

    // 修改日期
    private Date updateTime;

    // 修改人
    private String updateUserId;
}
