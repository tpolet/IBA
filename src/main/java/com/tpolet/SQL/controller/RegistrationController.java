package com.tpolet.SQL.controller;

import com.tpolet.SQL.domain.Role;
import com.tpolet.SQL.domain.User;
import com.tpolet.SQL.repos.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepos userRepos;

    @GetMapping("/registration")
    public String registraation() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        User fromDB = userRepos.findByUsername(user.getUsername());
        if (fromDB != null) {
            model.put("message", "User exists");
            return "regitration";
        }

        user.setActivity(true);

        user.setRoles(Collections.singleton(Role.ADMIN));

        userRepos.save(user);
        return "redirect:/login";
    }

}
