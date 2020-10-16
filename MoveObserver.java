package com.company;

public class MoveObserver extends Observer{
    public MoveObserver(Player player){
        this.player = player;
    }

    @Override
    public void update(int idx) {  //움직임이 감지 됐을 때 처리 구문
        switch(idx){
            case 0:     // 왼쪽
                player.setY(player.getY() - 2);
                player.setCnt(player.getCnt() + 1);
                System.out.println("왼쪽으로 이동 했습니다.");
                break;
            case 1:     // 오른쪽
                player.setY(player.getY() + 2);
                player.setCnt(player.getCnt() + 1);
                System.out.println("오른쪽으로 이동 했습니다.");
                break;
            case 2:     // 위쪽
                player.setX(player.getX() - 2);
                player.setCnt(player.getCnt() + 1);
                System.out.println("위쪽으로 이동 했습니다.");
                break;
            case 3:     // 아래쪽
                player.setX(player.getX() + 2);
                player.setCnt(player.getCnt() + 1);
                System.out.println("아래쪽으로 이동 했습니다.");
                break;
        }
    }
}

