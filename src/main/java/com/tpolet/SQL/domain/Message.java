package com.tpolet.SQL.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String log;
    private String result;

    public Message()  {
    }

    public Message(String log, String result) {
        this.log = log;
        this.result = result;
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
