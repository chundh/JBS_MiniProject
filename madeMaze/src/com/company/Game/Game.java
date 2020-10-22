package com.company.Game;

import com.company.Thread.TimeThread;
import com.company.map.map;
import com.company.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game {
    private int[][] myMap;
    private MapView view;
    private TimeThread timer;
    private Player player;
    private map map;

    public void initGame(){
        int n = Integer.parseInt(JOptionPane.showInputDialog("가로의 길이를 입력 해주세요."));
        int m = Integer.parseInt(JOptionPane.showInputDialog("세로의 길이를 입력 해주세요."));
        map = new map(n, m);
        myMap = map.createMap();
        view = new MapView(myMap, myMap.length, myMap[0].length);
        player.initPlayer(myMap);
        timer = new TimeThread();
    }

    public void createPlayer(){
        String playerName = JOptionPane.showInputDialog("플레이어의 이름을 입력하세요.\n (최대 8글자)");
        while(playerName.length() <= 0 || playerName.length() > 8){
            playerName = JOptionPane.showInputDialog("잘못된 입력입니다. 플레이어의 이름을 다시 입력하세요.\n  (최대 8글자)");
        }
//        if ()
        this.player = new Player(playerName);
    }

    public void playGame(){
        initGame();
        timer.start();                          // 타이머 스레드 실행
        view.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
            }
            @Override
            public void keyReleased(KeyEvent e) {
                int temp_X = player.getX();
                int temp_Y = player.getY();

                int idx = 0;
                if( e.getKeyCode() == 37 ) idx = 0;
                if( e.getKeyCode() == 38 ) idx = 2;
                if( e.getKeyCode() == 39 ) idx = 1;
                if( e.getKeyCode() == 40 ) idx = 3;
                view.panels[temp_X][temp_Y].setBackground(Color.WHITE);
                player.changePos(idx);
                view.panels[player.getX()][player.getY()].setBackground(Color.BLUE);


                if(myMap[player.getX()][player.getY()] == 2 || player.getCnt() > map.getCntLimit()) {
                    view.update(view.getGraphics());
                    view.removeKeyListener(this);                             // KeyListner 제거
                    timer.setTimer(false);                                      // 타이머 스레드 실행 종료

                    String sMessage = player.getName() + "님이 탈출에 성공했습니다.\n횟수 : " +
                            player.getCnt() + ", 걸린 시간 : " + timer.getSec() +
                            "초\n다시 플레이 하시겠습니까?\n  ( Yes / No ) ";
                    String sTitle = "Stage Clear!";
                    if(player.getCnt() > map.getCntLimit()){
                        sMessage = "탈출 미션에 실패 하셨습니다.\n다시 플레이 하시겠습니까?\n  ( Yes / No )";
                        sTitle = "Stage Failed!";
                    }

                    // Yes(재시작) =>  return 0 / No(게임 종료) => return 1
                    int nContinue = JOptionPane.showConfirmDialog(null, sMessage,
                            sTitle, JOptionPane.YES_NO_OPTION);

                    view.dispose();                                             // view 창 닫기

                    switch (nContinue){
                        case 0:             // 게임 재시작
                            view.removeAll();
                            playGame();
                            break;
                        case 1:             // 게임 종료
                            System.exit(0);
                            break;
                        default:
                            break;
                    }
                }
            }
        });
    }
}
