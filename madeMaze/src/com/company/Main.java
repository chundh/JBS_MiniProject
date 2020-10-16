package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Main {
    static int[][] myMap;
    public static void main(String[] args) {
        myMap = map.createMap();
        View view = new View(myMap, myMap.length, myMap[0].length);
        view.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }
            @Override
            public void keyPressed(KeyEvent e) {
                if( e.getKeyCode() == 37 ) {
                    System.out.println( "왼쪽 누름");
                    int idx = 3;
                }
                if( e.getKeyCode() == 38 ) {
                    System.out.println( "위 누름");
                    int idx = 2;
                }
                if( e.getKeyCode() == 39 ) {
                    System.out.println( "오른쪽 누름");
                    int idx = 1;
                }
                if( e.getKeyCode() == 40 ) {
                    System.out.println( "아래 누름");
                    int idx = 0;
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }
}
class View extends JFrame {
    public JPanel[][] panels;
    private Maze maze;
    public View(int[][] map, int row, int col) {
        maze = new Maze(map);
        panels = new JPanel[maze.getRow()][maze.getCol()];
        this.drawView();

    }
    /**
     * 뷰를 생성
     * */
    private void drawView() {
        this.getContentPane().setBackground(Color.DARK_GRAY);
        this.setSize(640, 640);
        this.setLayout(new FlowLayout());
        this.drawMaze(maze.getRow(), maze.getCol());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    /**
     * 미로를 View에 그림
     * */
    private void drawMaze(int row, int col) {
        this.setBackground(Color.DARK_GRAY);
        this.setLayout(new GridLayout(maze.getRow(), maze.getCol()));
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Color color = maze.getMap()[i][j] == 1 ?
                        Color.WHITE : Color.BLACK;
                if(maze.getMap()[i][j] == 2)
                    color = Color.RED;
                else if(maze.getMap()[i][j] == 3)
                    color = Color.BLUE;
                JPanel block = new JPanel();
                block.setBackground(color);
                if(j%2==0){
                    block.setSize(block.getWidth()/2, block.getHeight());
                }
                panels[i][j] = block;
                this.add(panels[i][j]);
            }
        }
    }
}

class Maze {
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