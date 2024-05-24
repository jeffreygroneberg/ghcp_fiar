package com.microsoft.germany.csu.fourinarow.api;

public enum GameStatus {

    PLAYER_ONE_WON,
    PLAYER_TWO_WON,
    DRAW,
    PLAYER_ONE_TURN,
    PLAYER_TWO_TURN,
    PLAYER_TWO_MISSING;

    // map enums to string and back
    public static GameStatus fromString(String status) {
        return GameStatus.valueOf(status);
    }

    public static String toString(GameStatus status) {
        return status.toString();
    }

}
