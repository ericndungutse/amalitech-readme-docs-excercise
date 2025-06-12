package com.ndungutse.documenting_project;

import java.util.Objects;

/**
 * Model class representing a task with title and description.
 */
public class TaskModel {
    private String title;
    private String description;

    /**
     * Default constructor
     */
    public TaskModel() {
    }

    /**
     * Parameterized constructor
     * 
     * @param title the title of the task
     * @param description the description of the task
     */
    public TaskModel(String title, String description) {
        this.title = title;
        this.description = description;
    }

    /**
     * Get the title of the task
     * 
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the title of the task
     * 
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get the description of the task
     * 
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description of the task
     * 
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "TaskModel{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskModel taskModel = (TaskModel) o;
        return Objects.equals(title, taskModel.title) &&
                Objects.equals(description, taskModel.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description);
    }
}
