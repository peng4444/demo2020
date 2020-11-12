package cn.pbj.demo2020.myblog.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.pbj.demo2020.myblog.common.DataResult;
import cn.pbj.demo2020.myblog.entity.Blog;
import cn.pbj.demo2020.myblog.service.BlogService;
import cn.pbj.demo2020.myblog.util.ShiroUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author peng4444
 * @since 2020-11-12
 */
@RestController
public class BlogController {
    @Autowired
    BlogService blogService;

    @GetMapping("/blogs")
    public DataResult blogs(Integer currentPage) {
        if(currentPage == null || currentPage < 1) {currentPage = 1;}
        //mybatisplus 分页插件 每页5条
        Page page = new Page(currentPage, 5);
        IPage pageData = blogService.page(page, new QueryWrapper<Blog>().orderByDesc("created"));
        return DataResult.succ(pageData);
    }

    @GetMapping("/blog/{id}")
    public DataResult detail(@PathVariable(name = "id") Long id) {
        Blog blog = blogService.getById(id);
        Assert.notNull(blog, "该博客已删除！");
        return DataResult.succ(blog);
    }

    @RequiresAuthentication//接口需要登录授权认证
    @PostMapping("/blog/edit")
    public DataResult edit(@Validated @RequestBody Blog blog) {
        System.out.println(blog.toString());
        Blog temp = null;
        if(blog.getId() != null) {
            //博客id存在，进行编辑
            temp = blogService.getById(blog.getId());
            // 只能编辑自己的文章
            Assert.isTrue(temp.getUserId().equals(ShiroUtil.getProfile().getId()), "没有权限编辑");
        } else {
            //博客id不存在，进行添加
            temp = new Blog();
            temp.setUserId(ShiroUtil.getProfile().getId());
            temp.setCreated(LocalDateTime.now());
            temp.setStatus(0);
        }
        BeanUtil.copyProperties(blog, temp, "id", "userId", "created", "status");
        blogService.saveOrUpdate(temp);
        return DataResult.succ("操作成功");

    }
}
