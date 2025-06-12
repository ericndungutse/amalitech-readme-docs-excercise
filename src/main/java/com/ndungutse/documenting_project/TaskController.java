package com.ndungutse.documenting_project;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

/**
 * REST controller for managing tasks
 */
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    // In-memory list to store tasks
    private final List<TaskModel> tasks = new ArrayList<>();

    /**
     * Constructor that initializes with some sample tasks
     */
    public TaskController() {
        // Add some sample tasks
        tasks.add(new TaskModel("Complete project", "Finish the Spring Boot project"));
        tasks.add(new TaskModel("Learn Docker", "Study Docker containerization"));
        tasks.add(new TaskModel("Write tests", "Create unit tests for the application"));
    }

    /**
     * Get all tasks
     * 
     * @return list of all tasks
     */
    @GetMapping
    public List<TaskModel> getAllTasks() {
        return tasks;
    }
}
