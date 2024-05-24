package com.microsoft.germany.csu.fourinarow.dtos;

public class CreateGameDTO {

    private String player1;

    public CreateGameDTO() {
    }

    public CreateGameDTO(String player1) {
        this.player1 = player1;
        
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

}
