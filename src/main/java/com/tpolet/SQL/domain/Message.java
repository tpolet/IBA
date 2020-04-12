package com.tpolet.SQL.domain;

import javax.persistence.*;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String log;
    private String result;

@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name="user_id")
private User author;

    public Message()  {
    }

    public Message(String log, String result) {
        this.log = log;
        this.result = result;
    }

    public Message(String log, User author) {
        this.log = log;
        this.author = author;
    }

     public Message(String log, String result, User author) {
        this.log = log;
        this.result = result;
        this.author = author;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Message(String log) {
        this.log = log;
    }

    public Integer getId() {
        return id;
    }

    public String getLog() {
        return log;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
