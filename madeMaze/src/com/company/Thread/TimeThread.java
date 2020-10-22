package com.company.Thread;

import java.util.concurrent.atomic.AtomicBoolean;

public class TimeThread extends Thread {
    private final AtomicBoolean running = new AtomicBoolean(false);
    private int sec = 0;

    @Override
    public void run() {
        //쓰레드에서 실행할 코드를 작성
        running.set(true);
        while(running.get()){
            try{
//                System.out.println(sec + "초");
//                System.out.println("Thread is running : " + sec + "sec");
                sec++;
                sleep(1000);
            } catch(InterruptedException e){
                currentThread().interrupt();
                System.out.println("Thread was interrupted");
            }
        }
    }

    public void setTimer(boolean onoff){
        this.running.set(onoff);
    }

    public int getSec(){
        return this.sec;
    }
}
