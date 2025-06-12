package com.ndungutse.documenting_project;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    /**
     * Get a single task by its title
     * 
     * @param title the title of the task to retrieve
     * @return the task with the specified title or 404 if not found
     */
    @GetMapping("/{title}")
    public ResponseEntity<TaskModel> getTaskByTitle(@PathVariable String title) {
        for (TaskModel task : tasks) {
            if (task.getTitle().equalsIgnoreCase(title)) {
                return ResponseEntity.ok(task);
            }
        }
        return ResponseEntity.notFound().build();
    }
}
