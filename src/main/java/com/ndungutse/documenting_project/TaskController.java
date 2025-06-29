package com.ndungutse.documenting_project;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    /**
     * Delete a task by its title
     * 
     * @param title the title of the task to delete
     * @return 200 OK if deleted successfully, 404 if task not found
     */
    @DeleteMapping("/{title}")
    public ResponseEntity<Void> deleteTaskByTitle(@PathVariable String title) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTitle().equalsIgnoreCase(title)) {
                tasks.remove(i);
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Update a task by its title
     * 
     * @param title the title of the task to update
     * @param updatedTask the updated task data
     * @return the updated task if found, or 404 if not found
     */
    @PutMapping("/{title}")
    public ResponseEntity<TaskModel> updateTaskByTitle(@PathVariable String title, @RequestBody TaskModel updatedTask) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTitle().equalsIgnoreCase(title)) {
                TaskModel task = tasks.get(i);
                task.setDescription(updatedTask.getDescription());
                // If the title in the request body is not null and not empty, update the title too
                if (updatedTask.getTitle() != null && !updatedTask.getTitle().isEmpty()) {
                    task.setTitle(updatedTask.getTitle());
                }
                return ResponseEntity.ok(task);
            }
        }
        return ResponseEntity.notFound().build();
    }
}
