package cn.pbj.demo2020.myblog.service.impl;

import cn.pbj.demo2020.myblog.entity.Blog;
import cn.pbj.demo2020.myblog.mapper.BlogMapper;
import cn.pbj.demo2020.myblog.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author peng4444
 * @since 2020-11-12
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
