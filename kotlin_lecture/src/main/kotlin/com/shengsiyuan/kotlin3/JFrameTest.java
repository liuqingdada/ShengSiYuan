package com.shengsiyuan.kotlin3;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Created by suhen
 * 18-12-9.
 * Email: 1239604859@qq.com
 */

class JFrameTest {

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("JFrame");

        JButton jButton = new JButton("button");

        jFrame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                System.out.println("win open");
            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("win closing");
            }

            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("win closed");
            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {
                System.out.println("win activated");
            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        jButton.addActionListener(System.out::println);

        jFrame.add(jButton);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
