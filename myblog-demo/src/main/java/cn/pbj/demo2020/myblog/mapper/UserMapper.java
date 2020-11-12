package cn.pbj.demo2020.myblog.mapper;

import cn.pbj.demo2020.myblog.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author peng4444
 * @since 2020-11-12
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
