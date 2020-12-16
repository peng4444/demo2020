package cn.pbj.demo2020.redis.one;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @pClassName: UserController
 * @author: pengbingjiang
 * @create: 2020/12/16 19:24
 * @description: TODO
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @PostMapping("/2")
    public User addUser2(@RequestBody User user){
        return userService.addUser2(user);
    }

    @GetMapping
    public User queryByUsername(@RequestParam("username") String username) {
        return userService.queryByUsername(username);
    }

    @DeleteMapping("/{username}")
    public void deleteByUsername(@PathVariable("username") String username){
        userService.deleteByUsername(username);
    }
}
