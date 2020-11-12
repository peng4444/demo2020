package cn.pbj.demo2020.myblog.common.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @pClassName: LoginDto
 * @author: pengbingjiang
 * @create: 2020/11/12 16:33
 * @description: TODO
 */
@Data
public class LoginDto implements Serializable {

    @NotBlank(message = "昵称不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;
}
