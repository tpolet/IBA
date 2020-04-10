package com.tpolet.SQL;

import com.tpolet.SQL.domain.Message;
import com.tpolet.SQL.repos.MessageRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class GreetingController {
    @Autowired
    MessageRepos messageRepos;

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

    @PostMapping("/main")
    public String add(@RequestParam String log, Map<String, Object> model) {
        Message message = new Message(log);
        messageRepos.save(message);
        Iterable<Message> logs = messageRepos.findAll();
        model.put("logs", logs);
        return "main";
    }

}
