package com.company;

public class MoveObserver extends Observer{
    public MoveObserver(Player player){
        this.player = player;
    }

    @Override
    public void update(int idx) {  //움직임이 감지 됐을 때 처리 구문
        int x = player.getX();
        int y = player.getY();
        int[][] map = player.getMap();
        switch(idx){
            case 0:     // 왼쪽
                y -= 1;
                if(map[x][y] == 0)
                    break;
                player.setY(player.getY() - 1);
                player.setCnt(player.getCnt() + 1);
                System.out.println("왼쪽으로 이동 했습니다.");
                break;
            case 1:     // 오른쪽
                y += 1;
                if(map[x][y] == 0)
                    break;
                player.setY(player.getY() + 1);
                player.setCnt(player.getCnt() + 1);
                System.out.println("오른쪽으로 이동 했습니다.");
                break;
            case 2:     // 위쪽
                x -= 1;
                if(map[x][y] == 0)
                    break;
                player.setX(player.getX() - 1);
                player.setCnt(player.getCnt() + 1);
                System.out.println("위쪽으로 이동 했습니다.");
                break;
            case 3:     // 아래쪽
                x += 1;
                if(map[x][y] == 0)
                    break;
                player.setX(player.getX() + 1);
                player.setCnt(player.getCnt() + 1);
                System.out.println("아래쪽으로 이동 했습니다.");
                break;
        }
    }
}

