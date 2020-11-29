package com.example.ramserver.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@RestController
public class TestController {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    private static final String URL = "jdbc:mysql://13.125.231.184:3306/rotten";

    private static final String USER ="rottenmaster";

    private static final String PW = "hellorottenam1028";

    @RequestMapping(path="/connectionexample")
    @ResponseBody
    //connection 설정후 데이터 뽑아내기
    public String connectionExample() throws Exception {
        Class.forName(DRIVER);
        try(Connection con= DriverManager.getConnection(URL,USER,PW)){
            Statement stmt=con.createStatement();
            ResultSet rs= ((Statement) stmt).executeQuery("select * from USER");
            while(rs.next()){
                String s=rs.getString("ID");
                String p=rs.getString("password");
                System.out.println(s+p);
            }
        }
        catch(Exception e){
            System.err.println(e);
        }
        return "helloeveryone";
    }


}
