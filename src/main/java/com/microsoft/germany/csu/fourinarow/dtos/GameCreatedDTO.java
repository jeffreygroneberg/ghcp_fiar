package com.microsoft.germany.csu.fourinarow.dtos;

import com.microsoft.germany.csu.fourinarow.api.GameStatus;

public record GameCreatedDTO(String gameId, String player1Name, String uuidPlayer1, String board,
        GameStatus gameStatus) {

    
}
