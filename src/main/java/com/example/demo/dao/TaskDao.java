package com.example.demo.dao;

import com.example.demo.DemoApplication;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Task;

@Repository
public class TaskDao {
    private final DemoApplication demoApplication;
    Connection conn;

    public TaskDao(DemoApplication demoApplication) {
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

    public List<Task> getAllTasks() {

        List<Task> list = new ArrayList<>();
        String sql = "SELECT * FROM tasks";

        try (
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
        ) {
            while(rs.next()) {
                Task task = new Task();
                task.setId(rs.getInt("id"));
                task.setTitle(rs.getString("title"));
                task.setCompleted(rs.getBoolean("completed"));
                list.add(task);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void addTask(String title) {
        String sql = "INSERT INTO tasks(title, completed) VALUES(?, false)";
        try (
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setString(1, title);
            pstmt.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteTask(int id) {
        String sql = "DELETE FROM tasks WHERE id = ?";
        try (
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void completeTask(int id) {
        String sql = "UPDATE tasks SET completed = true WHERE id = ?";
        try (
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Task getTaskById(int id) {
        Task task = new Task();
        task.setId(id);
        String sql = "SELECT title FROM tasks WHERE id = ?";
        try (
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                task.setTitle(rs.getString("title"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return task;
    }

    // public Task setTask(int id, String title) {
    //     Task task = new Task();
    //     task.setId(id);
    //     task.setTitle(title);
    //     return task;
    // }

    public void editTask(int id, String title) {
        String sql = "UPDATE tasks SET title = ? WHERE id = ?";
        try (
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setString(1, title);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
