package com.microsoft.germany.csu.fourinarow.services.fiar.impl;

import org.springframework.stereotype.Service;

import com.microsoft.germany.csu.fourinarow.api.GameStatus;
import com.microsoft.germany.csu.fourinarow.persistence.entities.FiarGame;
import com.microsoft.germany.csu.fourinarow.services.fiar.api.FiarService;

@Service
public class FiarServiceImpl implements FiarService {

    @Override
    public GameStatus getGameStatus(FiarGame game) {

        // check if both players
        if (game.getUuidPlayer2() == null) {
            return GameStatus.PLAYER_TWO_MISSING;
        }

        // check if player 1 has won
        return GameStatus.PLAYER_ONE_WON;

    }

    public boolean checkIfPlayerWonHorizontally(String player, String board) {

        // convert String to 2D array
        char[][] boardArray = convertStringTo2DArray(board);
        int size = boardArray.length;

        Character playerChar = player.charAt(0);

        // check if player has won horizontally by iterating through the rows
        for (int i = 0; i < size-3; i++) {

            for (int j = 0; j < size; j++) {
                if (boardArray[j][i] == playerChar && boardArray[j][i+1] == playerChar && boardArray[j][i+2] == playerChar && boardArray[j][i+3] == playerChar) {
                    return true;
                }
            }
          
        }

        return false;

    }

    // check if player has won vertically
    public boolean checkIfPlayerWonVertically(String player, String board) {

        // convert String to 2D array
        char[][] boardArray = convertStringTo2DArray(board);
        int size = boardArray.length;

        Character playerChar = player.charAt(0);

        // check if player has won vertically by iterating through the columns
        for (int i = 0; i < size-3; i++) {

            for (int j = 0; j < size; j++) {
                if (boardArray[i][j] == playerChar && boardArray[i+1][j] == playerChar && boardArray[i+2][j] == playerChar && boardArray[i+3][j] == playerChar) {
                    return true;
                }
            }
        
        }

        return false;

    }

    public boolean checkIfPlayerWonDiagonally(String player, String board) {

        // convert String to 2D array
        char[][] boardArray = convertStringTo2DArray(board);
        int size = boardArray.length;

        Character playerChar = player.charAt(0);

        // check if player has won diagonally by iterating through the diagonals
        for (int i = 0; i < size-3; i++) {

            for (int j = 0; j < size-3; j++) {
              
                if (boardArray[i][j] == playerChar && boardArray[i+1][j+1] == playerChar && boardArray[i+2][j+2] == playerChar && boardArray[i+3][j+3] == playerChar) {
                    return true;
                }

                if (boardArray[i][j+3] == playerChar && boardArray[i+1][j+2] == playerChar && boardArray[i+2][j+1] == playerChar && boardArray[i+3][j] == playerChar) {
                    return true;
                }
            }
        }

        return false;

    }

    // convert String to 2D array
    public static char[][] convertStringTo2DArray(String board) {

        // board is always square
        int size = (int) Math.sqrt(board.length());
        char[][] boardArray = new char[size][size];

        int index = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                boardArray[i][j] = board.charAt(index);
                index++;
            }
        }

        return boardArray;

    }

    // convert 2D array to String
    public static String convert2DArrayToString(char[][] boardArray) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < boardArray.length; i++) {
            for (int j = 0; j < boardArray.length; j++) {
                sb.append(boardArray[i][j]);
            }
        }

        return sb.toString();

    }

    public boolean makeMoveInColumn(String player, int column, String board) {

        // convert String to 2D array
        char[][] boardArray = convertStringTo2DArray(board);
        int size = boardArray.length;

        Character playerChar = player.charAt(0);

        // check if column is full
        if (boardArray[0][column] != '-') {
            return false;
        }

        // make move in column
        for (int i = size - 1; i >= 0; i--) {
            if (boardArray[i][column] == '-') {
                boardArray[i][column] = playerChar;
                break;
            }
        }

        return true;       

    }

}