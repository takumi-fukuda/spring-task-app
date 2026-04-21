package com.example.demo.controller;

import org.springframework.boot.web.server.autoconfigure.ServerProperties.Reactive.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Task;
import com.example.demo.entity.User;

import jakarta.servlet.http.HttpSession;

import com.example.demo.dao.TaskDao;
import com.example.demo.dao.UserDao;

import org.springframework.ui.Model;
import java.util.List;

@Controller
public class TaskController {
    private TaskDao taskDao;
    private UserDao userDao;

    public TaskController(TaskDao taskDao, UserDao userDao) {
        this.taskDao = taskDao;
        this.userDao = userDao;
    }

    @GetMapping("/tasks")
    public String showTask(Model model, HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        if(user != null) {
            int userId = user.getId();
            List<Task> tasks = taskDao.getTasksByUserId(userId);
            model.addAttribute("tasks", tasks);
            return "tasks";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/tasks/add")
    public String addTask(@RequestParam String title, Model model, HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        if(title.isEmpty()) {
            model.addAttribute("error", "タイトルを入力してください");
            List<Task> tasks = taskDao.getTasksByUserId(user.getId());
            model.addAttribute("tasks", tasks);
            return "tasks";
        }
        taskDao.addTask(title, user.getId());
        return "redirect:/tasks";
    }

    @PostMapping("/tasks/delete")
    public String deleteTask(@RequestParam int id) {
        taskDao.deleteTask(id);
        return "redirect:/tasks";
    }

    @PostMapping("/tasks/complete")
    public String completeTask(@RequestParam int id) {
        taskDao.completeTask(id);
        return "redirect:/tasks";
    }

    @GetMapping("/tasks/edit")
    public String editTask(@RequestParam int id, Model model) {
        Task task = taskDao.getTaskById(id);
        model.addAttribute("task", task);
        return "edit";
    }

    @PostMapping("/tasks/update")
    public String updateTask(@RequestParam int id, @RequestParam String title) {
        taskDao.editTask(id, title);
        return "redirect:/tasks";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model, HttpSession session) {
        User user = userDao.findByUsername(username);
        if(user != null && user.getPassword().equals(password)) {
            session.setAttribute("loginUser", user);
            return "redirect:/tasks";
        } else {
            model.addAttribute("error", "ログイン失敗");
            return "login";
        }
    }
}
