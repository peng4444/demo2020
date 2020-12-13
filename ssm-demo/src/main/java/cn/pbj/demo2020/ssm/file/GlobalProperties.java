package cn.pbj.demo2020.ssm.file;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @pClassName: GlobalProperties
 * @author: pengbingjiang
 * @create: 2020/12/13 21:19
 * @description: TODO
 */
@Data
@Component
public class GlobalProperties {

    /** 文件存放路径 */
    @Value("${pbj.file.path}")
    private String serverPath;

    /** 文件扩展名 */
    @Value("${pbj.file.extension}")
    private String extension;

}

