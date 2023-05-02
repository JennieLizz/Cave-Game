package com.jennielizz.cavegame.launcher;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class App {

    static Image bg;
    static Image shadow;
    static JFrame mainFrame = new JFrame("Cave Game!");
    static JLayeredPane mainPanel = new JLayeredPane();
    static JPanel shadow1 = new JPanel();

    public static void main(String[] args) {
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        try {
            bg = ImageIO.read(new File("res/textures/background.png")).getScaledInstance(80, 80, Image.SCALE_FAST);
            shadow = ImageIO.read(new File("res/textures/shadow.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Graphics g = mainPanel.getGraphics();
        g.drawImage(bg, 0, 0, mainPanel);
        g.drawImage(shadow, 0, 0, shadow1);

        mainPanel.setOpaque(false);
        mainFrame.setResizable(true);
        mainFrame.setSize(1280,720);
        mainFrame.setVisible(true);
        

        /*
        Manager gameWindow = new Manager();
        gameWindow.createGameWindow(1280,720, "Cave Game Launcher", "game");
        */
    }
}
