package cn.pbj.demo2020.ssm.file;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @pClassName: SysFileInfo
 * @author: pengbingjiang
 * @create: 2020/12/13 21:16
 * @description: TODO 文件信息表
 */
@Data
@Entity
@Table(name = "sys_file_info")
public class SysFileInfo implements Serializable {

    @Id
    @GeneratedValue
    private Integer fileId;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String filePath;

    @Column(nullable = false)
    private Long fileSize;
}
