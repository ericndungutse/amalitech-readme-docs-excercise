package com.ndungutse.documenting_project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
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
}