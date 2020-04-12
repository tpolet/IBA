package com.tpolet.SQL.controller;

import com.tpolet.SQL.domain.Message;
import com.tpolet.SQL.domain.Role;
import com.tpolet.SQL.domain.User;
import com.tpolet.SQL.repos.MessageRepos;
import com.tpolet.SQL.support.SQLSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    MessageRepos messageRepos;

    SQLSupport sqlSupport;

    @GetMapping("/")
    public String greeting(String name, Map<String, Object> model) {

        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {

        Iterable<Message> logs = messageRepos.findAll();
        model.put("logs", logs);

        return "main";
    }

//    @PostMapping("/main")
//    public String add(Principal user,
//                      @RequestParam String log,
//                      Map<String, Object> model) {
//
//        User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        Message message = new Message(log, u);
//        messageRepos.save(message);
//        Iterable<Message> logs = messageRepos.findAll();
//        model.put("logs", logs);
//        return "main";
//    }

    @PostMapping("/main")
    public String addMessage(Principal user,
                             @RequestParam String log,
                             Map<String, Object> model) {
        sqlSupport = new SQLSupport();
        User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!u.getRoles().contains(Role.ADMIN)) {
            if (sqlSupport.isSelect(log)) {
                Message message = sqlSupport.executeSQL(log, u);
                messageRepos.save(message);
            } else {
                model.put("warning", "NO PREMITION");
            }
        } else {
            Message message = sqlSupport.executeSQL(log, u);
            messageRepos.save(message);
        }

        Iterable<Message> logs = messageRepos.findAll();
        model.put("logs", logs);
        return "main";
    }

}
