package com.microsoft.germany.csu.fourinarow.persistence;

// testing the spring boot data jpa
import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import com.microsoft.germany.csu.fourinarow.persistence.entities.FiarGame;
import com.microsoft.germany.csu.fourinarow.persistence.repositories.FiarGameRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class GameTest {

    @Autowired
    private FiarGameRepository fiarGameRepository;

    @Test
    public void testCreateGame() {
        FiarGame game = new FiarGame();
        game.setBoard("----------------------------------------------------------------");
        game.setUiidPlayer1(UUID.randomUUID());
        fiarGameRepository.save(game);
        System.out.println(game.getId().toString());

        assertThat(game.getId()).isNotNull();
    }

    @Test
    public void testJoinGame() {

        FiarGame game = new FiarGame();
        game.setBoard("----------------------------------------------------------------");
        game.setUiidPlayer1(UUID.randomUUID());
        fiarGameRepository.save(game);

        FiarGame game2 = new FiarGame();
        game2.setBoard("----------------------------------------------------------------");
        game2.setUiidPlayer1(UUID.randomUUID());
        fiarGameRepository.save(game2);

        game.setUuidPlayer2(UUID.randomUUID());
        game.setPlayerTwoName("Player 2");

        assertThat(game.getId()).isNotNull();
    }

}