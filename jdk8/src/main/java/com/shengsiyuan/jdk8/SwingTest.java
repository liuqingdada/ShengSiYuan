package com.shengsiyuan.jdk8;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class SwingTest {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame("My JFrame");
        JButton jButton = new JButton("My JButton");

        jButton.addActionListener(event -> System.out.println(event.paramString()));

        jFrame.add(jButton);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
