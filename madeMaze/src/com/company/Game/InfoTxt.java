package com.company.Game;

import javax.swing.*;
import java.awt.*;

public class InfoTxt extends JFrame {
    private int curCnt;
    private int limCnt;
    private JLabel cnt;
    private JLabel max_cnt;
    private JFrame frame;

    public InfoTxt() {
        frame = new JFrame("Info");
        frame.setBounds(200, 200, 200, 80);
        frame.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel in = new JLabel("회수 카운트  ");
        cnt = new JLabel("Default Message");
        max_cnt = new JLabel("MAX Default Message");
        cnt.setText(String.valueOf(curCnt));
        cnt.setBounds(0, 20,cnt.getText().length(),30);
        max_cnt.setText(String.valueOf(limCnt));
        max_cnt.setBounds(0, 50,max_cnt.getText().length(),30);
        frame.add(in);
        frame.add(cnt);
        frame.add(max_cnt);
//        setVisible(true);
    }
    public void setcntText(String num){
        cnt.setText(num);
        cnt.update(cnt.getGraphics());
        frame.update(frame.getGraphics());
    }
    public void setmaxcntText(String  num){
        max_cnt.setText(num);
        max_cnt.update(max_cnt.getGraphics());
        frame.update(frame.getGraphics());
    }

    public JFrame getFrame() {
        return frame;
    }
}
