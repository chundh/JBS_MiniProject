package com.company;

import com.company.Game.Game;

public class Main {
    public static void main(String[] args) {

        Game mazeGame = new Game();

        mazeGame.createPlayer();
        mazeGame.playGame();

    }
}