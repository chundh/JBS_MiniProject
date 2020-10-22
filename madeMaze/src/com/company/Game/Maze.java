package com.company.Game;

public class Maze {
    private int[][] map;
    private int col, row;
    /**
     * 미로 객체 생성
     */
    public Maze(int[][] map) {
        this.map = map;
        this.col = map[0].length;
        this.row = map.length;
    }
    /**
     * 2차원 맵 반환
     */
    public int[][] getMap() {
        return map;
    }
    /**
     * 미로의 열 개수 반환
     */
    public int getCol() {
        return col;
    }
    /**
     * 미로의 행 개수 반환
     */
    public int getRow() {
        return row;
    }
}
