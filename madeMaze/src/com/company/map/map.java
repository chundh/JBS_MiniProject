package com.company.map;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class map {
    private boolean[][] check;
    private boolean[][] check2;
    private int[][] map;
    private final int[] dx = {0, -1, 0, 1};
    private final int[] dy = {-1, 0, 1, 0};
    private final int[] dx2 = {0, -1, 0, 1};
    private final int[] dy2 = {-1, 0, 1, 0};

    public ArrayList<point> getItemZone() {
        return itemZone;
    }

    private ArrayList<point> itemZone = new ArrayList<>();
    private int n = 0;
    private int m = 0;
    private int minCnt = 0;
    private int cntLimit = 0;

    public map(int n, int m){
        this.n = n;
        this.m = m;
    }

    public int[][] createMap() {
        map = new int[n + n+1][m + m+1];
        check = new boolean[map.length][map[0].length];
        check2 = new boolean[map.length][map[0].length];
        check2[0][1] = true;
        map[0][1] = 3;
        map[1][1] = 1;
        map[map.length-1][map[0].length-2] = 2; //도착지
        map = dfs(map, 1,1, 0);
        minCnt = bfs(map, 1, 1);
        cntLimit = minCnt + ((map.length + map[0].length) / 2);
        return map;
    }

    private int bfs(int[][] map, int x, int y){
        //최단거리 ( Minimum Distance Count ) = mdc
        int mdc = 0;
        Queue<point> queue = new LinkedList<>();
        int[][] cntMap = new int[map.length][map[0].length];

        cntMap[1][1] = 1;
        queue.add(new point(x,y));

        while(!queue.isEmpty()){
            point p = queue.poll();
            int nCnt = 0;

            check2[p.x][p.y] = true;


            for(int i = 0; i < 4; i++){
                int tmp_x = p.x + dx[i];
                int tmp_y = p.y + dy[i];

                // 아이템 생성 할 수 있는 위치 List
                if(map[tmp_x][tmp_y] == 0){
                    nCnt++;
                    if(nCnt >= 3){
                        itemZone.add(new point(p.x, p.y));
                    }
                }

                if(!(check2[tmp_x][tmp_y]) && map[tmp_x][tmp_y] == 1){
                    queue.add(new point(tmp_x, tmp_y));
                    cntMap[tmp_x][tmp_y] = cntMap[p.x][p.y] + 1;
                }
            }
        }
        mdc = cntMap[map.length - 2][map[0].length - 2] + 1;
        return mdc;
    }

    private int[][] dfs(int[][] map, int x, int y, int cnt){
        if(cnt==n*m){
            return map;
        }
        check[x][y] = true;
        if(checkFour(x, y))             // 현재 지점에서 4방향 갈 수 있는 방향인지 확인
            return map;                 // (4방향 중 check가 true인 곳이 있는지 / 지도 범위 안에 있는지/)
        while(true) {
            int idx = (int) Math.floor(Math.random() * 4);
            int temp_x = x + 2*dx[idx];
            int temp_y = y + 2*dy[idx];
            if (checkRange(temp_x, temp_y)) {
                if (!check[temp_x][temp_y]) {
                    map[x+dx[idx]][y+dy[idx]] = 1;
                    map[temp_x][temp_y] = 1;
                    dfs(map, temp_x, temp_y, cnt+1);
                }
            }
            if(checkFour(x,y))
                break;
        }
        return map;
    }
    private boolean checkRange(int x, int y){ // map의 지도 범위 안에 있는지 확인
        if(x>=map.length-1 || x<1 || y>=map[0].length-1 || y<1){
            return false;
        }
        return true;
    }

    private boolean checkFour(int x, int y){
        for(int i=0; i<4; i++){
            int temp_x = x + 2*dx[i];
            int temp_y = y + 2*dy[i];
            if(checkRange(temp_x,temp_y)){
                if(!check[temp_x][temp_y])
                    return false;
            }
        }
        return true;
    }

    public void setCntLimit(int cntLimit) {
        this.cntLimit = cntLimit;
    }

    public int getMinCnt() {
        return minCnt;
    }

    public int getCntLimit(){
        return this.cntLimit;
    }
}
