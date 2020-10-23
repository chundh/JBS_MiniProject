package com.company.Game;

import com.company.Thread.TimeThread;
import com.company.map.map;
import com.company.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Game {
    private int[][] myMap;
    private MapView view;
    private TimeThread timer;
    private Player player;
    private map map;
    private InfoTxt info;
    public void checkInput(String input){
        while(input == null) {
            if (input == null) {
                JOptionPane.showMessageDialog(null, "게임을 종료합니다.", "게임 종료", JOptionPane.WARNING_MESSAGE);
                System.exit(0);
            }
            char[] arr = input.toCharArray();
            boolean isNum = false;
            for (int i = 0; i < input.length(); i++) {
                if (arr[i] < 48 && arr[i] > 57) {
                    isNum = false;
                    break;
                }
            }
        }
    }

    public void initGame(){
        String s1 = JOptionPane.showInputDialog("가로의 길이를 입력 해주세요.");
//        checkInput(s1);
        String s2 = JOptionPane.showInputDialog("세로의 길이를 입력 해주세요.");
//        checkInput(s2);
        int n = Integer.parseInt(s1);
        int m = Integer.parseInt(s2);
        map = new map(n, m);
        myMap = map.createMap();
        setItemzone();
        view = new MapView(myMap, myMap.length, myMap[0].length);
        player.initPlayer(myMap);
        timer = new TimeThread();
        info = new InfoTxt();
        info.getFrame().setVisible(true);
        info.getFrame().setLocation(view.getLocation().x + view.getWidth(),0);
    }

    public void setItemzone(){
        System.out.println(map.getItemZone().size());
        int[] idxArr = new int[map.getItemZone().size()];
        Random rand = new Random();
        int cnt = 0;
        if(map.getItemZone().size() == 1){
            idxArr[0] = 1;
        } else if (map.getItemZone().size() > 1) {
            while(true){

                int num = rand.nextInt(map.getItemZone().size());// 3 4 3 5 7
                if(idxArr[num] == 0){
                    idxArr[num] = 1;
                    cnt++;
                }
                if(cnt == map.getItemZone().size()/2)
                    break;
            }
        }
        for (int i = 0; i < idxArr.length; i++) {
            if(idxArr[i] == 1){
                int x = map.getItemZone().get(i).getX();
                int y = map.getItemZone().get(i).getY();
                myMap[x][y] = 5;
                System.out.println(x + ", " + y);
            }
        }
        for(int i = 0; i < myMap.length; i++){
            for(int j = 0; j < myMap[0].length; j++){
                System.out.print(myMap[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void createPlayer(){
        String playerName = JOptionPane.showInputDialog("플레이어의 이름을 입력하세요.\n (최대 8글자)");
        while(playerName == null || playerName.length() <= 0 || playerName.length() > 8){
            if (playerName == null){
                JOptionPane.showMessageDialog(null, "게임을 종료합니다.", "게임 종료", JOptionPane.WARNING_MESSAGE);
                System.exit(0);
            }
            playerName = JOptionPane.showInputDialog("잘못된 입력입니다. 플레이어의 이름을 다시 입력하세요.\n  (최대 8글자)");
        }
        this.player = new Player(playerName);
    }

    public void playGame(){
        initGame();
        timer.start();                          // 타이머 스레드 실행
        info.setmaxcntText(String.valueOf(map.getCntLimit()));
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
                info.setcntText(String.valueOf(player.getCnt()) + "  / ");

                System.out.println("회수 카운트 " + player.getCnt() + " / " + map.getCntLimit());
                if(myMap[player.getX()][player.getY()] == 5){
                    JOptionPane.showMessageDialog(null, "회수 추가 아이템 획득!", "아이템 획득", JOptionPane.INFORMATION_MESSAGE);
                    int originCnt = map.getCntLimit();
                    map.setCntLimit(originCnt + myMap.length);
                    System.out.println("제한 회수 변경 " + originCnt + "회 -> " + map.getCntLimit() + "회");
                    myMap[player.getX()][player.getY()] = 1;
                    info.setmaxcntText(String.valueOf((map.getCntLimit())));
                }

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
