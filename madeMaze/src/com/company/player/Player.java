package com.company.player;

public class Player {
    private int x;
    private int y;
    private int cnt;
    private String name;
    private Observer observer;
    private int[][] map;
    public Player(String playerName) {
        this.x = 0;
        this.y = 1;
        this.cnt = 0;
        this.name = playerName;
        this.observer = new MoveObserver(this);
    }

    public void initPlayer(int [][] map){
        this.x = 0;
        this.y = 1;
        this.cnt = 0;
        this.map = map;
    }

    public void changePos(int idx){
        notifyObserver(idx);
    }

    public void notifyObserver(int idx){
        observer.update(idx);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public int[][] getMap() { return map;}
}
