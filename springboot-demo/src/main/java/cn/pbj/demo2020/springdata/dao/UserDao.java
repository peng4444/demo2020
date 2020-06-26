package cn.pbj.demo2020.springdata.dao;

import cn.pbj.demo2020.springdata.model.User;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * @pClassName: UserDao
 * @author: pengbingjiang
 * @create: 2020/6/26 10:43
 * @description: TODO
 */
//@RepositoryDefinition(domainClass = User.class, idClass = Long.class)
public interface UserDao extends Repository<User,Long> {
    User getUserById(Long id);

    List<User> findAll();
}
