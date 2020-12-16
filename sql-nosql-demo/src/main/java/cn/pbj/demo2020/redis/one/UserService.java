package cn.pbj.demo2020.redis.one;

import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @pClassName: UserService
 * @author: pengbingjiang
 * @create: 2020/12/16 19:24
 * @description: TODO
 */
@Service
@CacheConfig(cacheNames = "users")
public class UserService {

    private static Map<String, User> userMap = new HashMap<>();

    @CachePut(key = "#user.username")
    public User addUser(User user){
        user.setUid(UUID.randomUUID().toString());
        System.out.println("add user: " + user);
        userMap.put(user.getUsername(), user);
        return user;
    }

    @Caching(put = {
            @CachePut( key = "#user.username"),
            @CachePut( key = "#user.uid")
    })
    public User addUser2(User user) {
        user.setUid(UUID.randomUUID().toString());
        System.out.println("add user2: " + user);
        userMap.put(user.getUsername(), user);
        return user;
    }

    @Cacheable( key = "#p0", condition = "#p0 != null")
    public User queryByUsername(String username) {
        System.out.println("query from map...");
        return userMap.get(username);
    }

    @CacheEvict(key = "#username", condition = "#username != null")
    public void deleteByUsername(String username) {
        userMap.remove(username);
    }
}
