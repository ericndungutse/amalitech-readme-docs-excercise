package com.ndungutse.documenting_project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaskControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetTaskByTitle() {
        // Test getting an existing task
        ResponseEntity<TaskModel> response = restTemplate.getForEntity(
                "http://localhost:" + port + "/api/tasks/Learn Docker",
                TaskModel.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Learn Docker", response.getBody().getTitle());
        assertEquals("Study Docker containerization", response.getBody().getDescription());

        // Test getting a non-existent task
        ResponseEntity<TaskModel> notFoundResponse = restTemplate.getForEntity(
                "http://localhost:" + port + "/api/tasks/Non-existent Task",
                TaskModel.class);

        assertEquals(HttpStatus.NOT_FOUND, notFoundResponse.getStatusCode());
    }

    @Test
    public void testDeleteTaskByTitle() {
        // First verify the task exists
        ResponseEntity<TaskModel> getResponse = restTemplate.getForEntity(
                "http://localhost:" + port + "/api/tasks/Write tests",
                TaskModel.class);
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());

        // Delete the task
        ResponseEntity<Void> deleteResponse = restTemplate.exchange(
                "http://localhost:" + port + "/api/tasks/Write tests",
                HttpMethod.DELETE,
                HttpEntity.EMPTY,
                Void.class);
        assertEquals(HttpStatus.OK, deleteResponse.getStatusCode());

        // Verify the task is deleted
        ResponseEntity<TaskModel> getAfterDeleteResponse = restTemplate.getForEntity(
                "http://localhost:" + port + "/api/tasks/Write tests",
                TaskModel.class);
        assertEquals(HttpStatus.NOT_FOUND, getAfterDeleteResponse.getStatusCode());

        // Try to delete a non-existent task
        ResponseEntity<Void> deleteNonExistentResponse = restTemplate.exchange(
                "http://localhost:" + port + "/api/tasks/Non-existent Task",
                HttpMethod.DELETE,
                HttpEntity.EMPTY,
                Void.class);
        assertEquals(HttpStatus.NOT_FOUND, deleteNonExistentResponse.getStatusCode());
    }
}
