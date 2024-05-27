package com.microsoft.germany.csu.fourinarow.api;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microsoft.germany.csu.fourinarow.dtos.CreateGameDTO;
import com.microsoft.germany.csu.fourinarow.dtos.FiarGameDTO;
import com.microsoft.germany.csu.fourinarow.dtos.GameCreatedDTO;
import com.microsoft.germany.csu.fourinarow.dtos.JoinGameDTO;
import com.microsoft.germany.csu.fourinarow.persistence.entities.FiarGame;
import com.microsoft.germany.csu.fourinarow.services.fiar.api.FiarService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/games")
public class GameAPI {


    private final CrudRepository<FiarGame, UUID> crudRepository;

    @Autowired
    public GameAPI(FiarService fiarService, CrudRepository<FiarGame, UUID> crudRepository) {
        this.crudRepository = crudRepository;
    }

    @PostMapping
    public ResponseEntity<GameCreatedDTO> createGame(@RequestBody CreateGameDTO fiarGame) {

        FiarGame newGame = new FiarGame();
        newGame.setBoard("----------------------------------------------------------------");
        newGame.setUiidPlayer1(UUID.randomUUID());
        newGame.setPlayerOneName(fiarGame.getPlayer1());

        crudRepository.save(newGame);

        GameCreatedDTO dto = new GameCreatedDTO(newGame.getId().toString(), newGame.getPlayerOneName(),
                newGame.getUiidPlayer1().toString(), newGame.getBoard(), GameStatus.PLAYER_TWO_MISSING);

        return ResponseEntity.ok(dto);

    }

    // Create a game for player2 using the uuid of the game
    @Transactional
    @PostMapping("/{gameId}")
    public ResponseEntity<GameCreatedDTO> joinGame(@RequestBody JoinGameDTO joinGameDTO) {

        FiarGame game = crudRepository.findById(UUID.fromString(joinGameDTO.gameId())).orElseThrow();

        // determine randomly if player 1 or player 2 starts

        game.setUuidPlayer2(UUID.randomUUID());
        game.setPlayerTwoName(joinGameDTO.player2Name());

        // randomly determine if player 1 or player 2 should start, but all in one line
        game.setStatus(Math.random() > 0.5 ? GameStatus.PLAYER_ONE_TURN : GameStatus.PLAYER_TWO_TURN);

        crudRepository.save(game);

        GameCreatedDTO dto = new GameCreatedDTO(game.getId().toString(), game.getPlayerOneName(),
                game.getUiidPlayer1().toString(), game.getBoard(), game.getStatus());

        return ResponseEntity.ok(dto);

    }

    @GetMapping("/{gameId}/{playerId}")
    public ResponseEntity<FiarGameDTO> getGameByIdAndPlayerId(String gameId, String playerId) {

        if (gameId == null || playerId == null) {
            return ResponseEntity.badRequest().build();
        }

        FiarGame game = crudRepository.findById(UUID.fromString(gameId)).orElseThrow();

        // check if uuid is within the players of the game
        if (!game.getUiidPlayer1().toString().equals(playerId) && !game.getUuidPlayer2().toString().equals(playerId)) {

            // return bad request and error message that the player is not part of the game
            return ResponseEntity.badRequest().build();

        }

        return ResponseEntity.ok(new FiarGameDTO(game.getPlayerOneName(), game.getPlayerTwoname(), game.getId(),
                game.getUiidPlayer1().toString(), "", game.getBoard(),
                game.getStatus()));

    }
}
