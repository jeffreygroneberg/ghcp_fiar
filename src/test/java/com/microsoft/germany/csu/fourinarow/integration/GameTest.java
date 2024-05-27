package com.microsoft.germany.csu.fourinarow.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.apache.catalina.connector.Response;

import com.microsoft.germany.csu.fourinarow.api.GameStatus;
import com.microsoft.germany.csu.fourinarow.dtos.CreateGameDTO;
import com.microsoft.germany.csu.fourinarow.dtos.GameCreatedDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GameTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testCreateGame() {

        CreateGameDTO createGameDTO = new CreateGameDTO("Player 1");
        ResponseEntity<String> gameCreatedDTO = testRestTemplate.postForEntity("/api/games", createGameDTO,
                String.class);


        assertNotNull(gameCreatedDTO);

    }

}
