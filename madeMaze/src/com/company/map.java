package com.company;

import java.util.Scanner;

public class map {
    static point start;
    static point end;
    static boolean[][] check;
    static int[][] map;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    static int n;
    static int m;
    static class point{
        int x;
        int y;

        public point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static int[][] createMap() {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        map = new int[n + n+1][m + m+1];
        check = new boolean[map.length][map[0].length];
        start = new point(0,0);
        end = new point(3,3);
        map[0][1] = 3;
        map[1][1] = 1;
        map[map.length-1][map[0].length-2] = 2;
        map = dfs(map, 1,1, 0);
        for(int i=0; i<map.length;i++){
            for(int j=0; j<map[0].length; j++){
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
        return map;
    }
    private static int[][] dfs(int[][] map, int x, int y, int cnt){
        if(cnt==n*m){
            return map;
        }
        check[x][y] = true;
        if(checkFour(x, y))
            return map;
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
    private static boolean checkRange(int x, int y){ // map의 지도 범위 안에 있는지 확인
        if(x>=map.length-1 || x<1 || y>=map[0].length-1 || y<1){
            return false;
        }
        return true;
    }

    private static boolean checkFour(int x, int y){
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

}
