package com.jennielizz.cavegame.launcher;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class App {

    static Image bg;
    static Image shadow;
    static JFrame mainFrame = new JFrame("Cave Game!");
    static JPanel mainPanel = new JPanel();
    static JPanel shadow1 = new JPanel();

    public void paintComponent(Graphics g) {
        int width = 1280;
        int height = 720;
        for (int x = 0; x < width; x += 80) {
            for (int y = 0; y < height; y += 80) {
                g.drawImage(bg, x, y, mainPanel);
            }
        }

        
    }

    public static void main(String[] args) {
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        try {
            bg = ImageIO.read(new File("res/textures/background.png")).getScaledInstance(80, 80, Image.SCALE_FAST);
            shadow = ImageIO.read(new File("res/textures/shadow.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        mainFrame.setLayout(new BorderLayout());
        mainFrame.add(mainPanel);
      
        
        mainFrame.setResizable(true);
        mainFrame.setSize(1280,720);
        mainFrame.setVisible(true);


        /*
        Manager gameWindow = new Manager();
        gameWindow.createGameWindow(1280,720, "Cave Game Launcher", "game");
        */
    }
}
