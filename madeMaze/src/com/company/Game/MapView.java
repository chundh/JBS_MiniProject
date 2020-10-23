package com.company.Game;

import javax.swing.*;
import java.awt.*;

public class MapView extends JFrame {
    public JPanel[][] panels;
    private JFrame infoTxt;
    private Maze maze;
    public MapView(int[][] map, int row, int col) {
        maze = new Maze(map);
        panels = new JPanel[maze.getRow() + 1][maze.getCol() + 1];
        this.drawView();

    }
    /**
     * 뷰를 생성
     * */
    private void drawView() {
        this.getContentPane().setBackground(Color.DARK_GRAY);
        this.setSize(maze.getCol()*30, maze.getRow()*30);
        this.setLayout(new FlowLayout());
        this.drawMaze(maze.getRow(), maze.getCol());
//        infoTxt = new InfoTxt();
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
                else if(maze.getMap()[i][j] == 5)
                    color = Color.YELLOW;
                JPanel block = new JPanel();
                block.setBackground(color);
                if(j % 2 == 0){
                    block.setSize(block.getWidth()/2, block.getHeight());
                }
                panels[i][j] = block;
                this.add(panels[i][j]);
            }
        }
    }
}
