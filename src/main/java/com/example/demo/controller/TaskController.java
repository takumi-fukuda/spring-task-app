package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Task;
import com.example.demo.dao.TaskDao;

import org.springframework.ui.Model;
import java.util.List;

@Controller
public class TaskController {
    private TaskDao taskDao;

    public TaskController(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @GetMapping("/tasks")
    public String showTask(Model model) {
        List<Task> tasks = taskDao.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    @PostMapping("/tasks/add")
    public String addTask(@RequestParam String title) {
        taskDao.addTask(title);
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
}
