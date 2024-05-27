package com.microsoft.germany.csu.fourinarow.service;

import org.junit.jupiter.api.Test;

import com.microsoft.germany.csu.fourinarow.services.fiar.impl.FiarServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

public class FiarServiceTest {

    @Test
    public void testCheckIfPlayerWonHorizontally() {
        FiarServiceImpl fiarService = new FiarServiceImpl();

        String board = "----------------------------------------------------------------";
        String player = "X";

        boolean result = fiarService.checkIfPlayerWonHorizontally(player, board);

        assertFalse(result);

        board = "XXXX----------------------------------------------------------------";
        result = fiarService.checkIfPlayerWonHorizontally(player, board);

        assertTrue(result);

        // another win the botton row
        board = "----------------------------------------------------------------XXXX";
        result = fiarService.checkIfPlayerWonHorizontally(player, board);

        // no win scattered
        board = "X--X--X--X---------------------------------------------------------";
        result = fiarService.checkIfPlayerWonHorizontally(player, board);

    }

    @Test
    public void testCheckIfPlayerWonVertically() {

        FiarServiceImpl fiarService = new FiarServiceImpl();

        // board is always max 64 characters
        String board = "----------------------------------------------------------------";
        String player = "X";
        
        boolean result = fiarService.checkIfPlayerWonVertically(player, board);

        assertFalse(result);

        // win in the first column board is always max 64 characters
        board = "X-------X-------X-------X----------------------------------------";
        result = fiarService.checkIfPlayerWonVertically(player, board);

        assertTrue(result);

        // win in the last column board is always max 64 characters
        board = "-------X-------X-------X-------X--------------------------------";

        result = fiarService.checkIfPlayerWonVertically(player, board);

        assertTrue(result);
        
        // win in the middle column board is always max 64 characters
        board = "------X-------X-------X-------X---------------------------------";

        result = fiarService.checkIfPlayerWonVertically(player, board);

        assertTrue(result);

        // no win scattered
        board = "X--X--X--X------------------------------------------------------";

    }

    @Test
    public void testCheckIfPlayerWonDiagonally() {

        FiarServiceImpl fiarService = new FiarServiceImpl();

        // initialize board as char array
        char[][] board = new char[8][8];

        // fill board with empty fields
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = '-';
            }
        }

        // win in the first diagonal
        board[0][0] = 'X';
        board[1][1] = 'X';
        board[2][2] = 'X';
        board[3][3] = 'X';

        boolean result = fiarService.checkIfPlayerWonDiagonally("X", FiarServiceImpl.convert2DArrayToString(board) );

        // win bottom corner right clear board
        board = new char[8][8];

        // fill board with empty fields
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = '-';
            }
        }

        board[7][7] = 'X';
        board[6][6] = 'X';
        board[5][5] = 'X';
        board[4][4] = 'X';

        assertTrue(result);      
        
        // win somewhere in the middle
        board = new char[8][8];

        // fill board with empty fields
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = '-';
            }
        }

        board[0][7] = 'X';
        board[1][6] = 'X';
        board[2][5] = 'X';
        board[3][4] = 'X';
        
        result = fiarService.checkIfPlayerWonDiagonally("X", FiarServiceImpl.convert2DArrayToString(board) );
        

    }
}