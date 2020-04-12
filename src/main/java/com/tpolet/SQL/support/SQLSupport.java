package com.tpolet.SQL.support;

import com.tpolet.SQL.domain.Message;
import com.tpolet.SQL.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SQLSupport {

    Connection conn;
    {
        try {

            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException d) {
                System.out.println("Error... ");
            }

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/GoodStorage", "root", "33333333");
            // statement = conn.createStatement();
            System.out.println("Connected... ");
        } catch (SQLException d) {
            System.out.println("Error connection... ");
        }
    }


    public boolean isSelect(String log) {
        Pattern notselect = Pattern.compile("(INSERT|insert|UPDATE|update|DELETE|delete|DROP|drop|CREATE|create)");
        Matcher matcher = notselect.matcher(log);

        if (matcher.find()) {
            return false;
        } else {
            return true;
        }

    }

    public Message executeSQL(String log,User user){
        String result="";

        try{
            Statement statement = conn.createStatement();
           // ResultSet set = statement.executeQuery("select * from GoodStorage..Goods ");
            ResultSet set = statement.executeQuery(log);
            result=set.toString();
        } catch (SQLException sql){ result="Error";}

        return new Message(log,result,user);
    }
}
