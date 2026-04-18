package com.example.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.springframework.stereotype.Repository;

import com.example.demo.DemoApplication;
import com.example.demo.entity.User;

@Repository
public class UserDao {
    private final DemoApplication demoApplication;
    Connection conn;
    public UserDao(DemoApplication demoApplication) {
        try {
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/taskapp",
                "root",
                "root1234root"
            );
        } catch(Exception e) {
            e.printStackTrace();
        }
        this.demoApplication = demoApplication;
    }

    public User findByUsername(String username) {
        User user = null;
        String sql = "SELECT * FROM users WHERE username = ?";
        try(
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return user; 
    }
}
