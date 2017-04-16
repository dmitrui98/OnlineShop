package by.dmitrui98.controller.json;


import by.dmitrui98.entity.User;
import by.dmitrui98.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserController {

    @Autowired
    private BaseService<User, Long> userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getUsers() {
        List<User> list = userService.getAll();
        System.out.println(list);
        return list;
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable("id") long userId) {
        return (User) userService.getById((Long) userId);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public void saveUser(@RequestBody User user) {
        userService.save(user);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public void removeUser(@PathVariable("id") long userId) {
        userService.remove((Long) userId);
    }
}
