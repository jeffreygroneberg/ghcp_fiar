package com.microsoft.germany.csu.fourinarow.dtos;

import java.util.UUID;

import com.microsoft.germany.csu.fourinarow.api.GameStatus;

public record FiarGameDTO(String playerOneName, String playerTwoName, UUID id, String playerUUID, String currentPlayer,
        String board, GameStatus status) {

}
