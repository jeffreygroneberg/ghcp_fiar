package com.microsoft.germany.csu.fourinarow.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.UUID;

import com.microsoft.germany.csu.fourinarow.api.GameStatus;

import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class FiarGame {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private UUID uiidPlayer1;
    private UUID uuidPlayer2;


    private String board;

    private String playerOneName;
    private String playerTwoname;

    @Enumerated(EnumType.STRING)
    private GameStatus status;

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public FiarGame() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUiidPlayer1() {
        return uiidPlayer1;
    }

    public void setUiidPlayer1(UUID uiidPlayer1) {
        this.uiidPlayer1 = uiidPlayer1;
    }

    public UUID getUuidPlayer2() {
        return uuidPlayer2;
    }

    public void setUuidPlayer2(UUID uuidPlayer2) {
        this.uuidPlayer2 = uuidPlayer2;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getPlayerOneName() {
        return playerOneName;
    }

    public void setPlayerOneName(String playerOneName) {
        this.playerOneName = playerOneName;
    }

    public String getPlayerTwoname() {
        return playerTwoname;
    }

    public void setPlayerTwoName(String playerTwoname) {
        this.playerTwoname = playerTwoname;
    }

}
