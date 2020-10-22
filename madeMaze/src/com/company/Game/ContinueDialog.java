package com.company.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContinueDialog extends JFrame {

    public ContinueDialog() {
        JButton yesBtn = new JButton("Yes");
        JButton noBtn =  new JButton("No");
        JPanel continuePannel = new JPanel();
        ActionListener handler = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equals("Yes")){         // Yes 눌렸을 시

                } else if (e.getActionCommand().equals("No")){  // No 눌렸을 시

                }
            }
        };
    }
}
