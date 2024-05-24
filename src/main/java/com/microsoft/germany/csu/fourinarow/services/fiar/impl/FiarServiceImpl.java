package com.microsoft.germany.csu.fourinarow.services.fiar.impl;

import com.microsoft.germany.csu.fourinarow.api.GameStatus;
import com.microsoft.germany.csu.fourinarow.persistence.entities.FiarGame;
import com.microsoft.germany.csu.fourinarow.services.fiar.api.FiarService;

public class FiarServiceImpl implements FiarService {

    @Override
    public GameStatus getGameStatus(FiarGame game) {

        // check if both players
        if (game.getUuidPlayer2() == null) {
            return GameStatus.PLAYER_TWO_MISSING;
        }

        // check if player 1 has won
        return null;

    }

    private boolean checkIfGameIsDraw(FiarGame game) {
        return game.getBoard().chars().noneMatch(c -> c == '-');
    }

    private boolean checkIfColumnIsFull(FiarGame game, int column) {
        return game.getBoard().chars().filter(c -> c == '-').count() == 0;
    }

    private boolean checkIfPlayerHasWon(FiarGame game, char player) {

        // check horizontal
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 4; col++) {
                if (game.getBoard().charAt(row * 7 + col) == player
                        && game.getBoard().charAt(row * 7 + col + 1) == player
                        && game.getBoard().charAt(row * 7 + col + 2) == player
                        && game.getBoard().charAt(row * 7 + col + 3) == player) {
                    return true;
                }
            }
        }

        // check vertical
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 7; col++) {
                if (game.getBoard().charAt(row * 7 + col) == player
                        && game.getBoard().charAt((row + 1) * 7 + col) == player
                        && game.getBoard().charAt((row + 2) * 7 + col) == player
                        && game.getBoard().charAt((row + 3) * 7 + col) == player) {
                    return true;
                }
            }
        }

        // check diagonal
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 4; col++) {
                if (game.getBoard().charAt(row * 7 + col) == player
                        && game.getBoard().charAt((row + 1) * 7 + col + 1) == player
                        && game.getBoard().charAt((row + 2) * 7 + col + 2) == player
                        && game.getBoard().charAt((row + 3) * 7 + col + 3) == player) {
                    return true;
                }
            }
        }

        // check diagonal
        for (int row = 0; row < 3; row++) {
            for (int col = 3; col < 7; col++) {
                if (game.getBoard().charAt(row * 7 + col) == player
                        && game.getBoard().charAt((row + 1) * 7 + col - 1) == player
                        && game.getBoard().charAt((row + 2) * 7 + col - 2) == player) {
                    return true;
                }
            }
        }

        return false;
    }

}