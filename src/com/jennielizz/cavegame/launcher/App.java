package com.jennielizz.cavegame.launcher;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class App extends JPanel{

    static Image bg;
    static Image shadow;
    static JFrame mainFrame = new JFrame("Cave Game!");
    static JPanel mainPanel = new JPanel();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bg, 0, 0, null);
    }

    public static void main(String[] args) {
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1280,720);

        try {
            bg = ImageIO.read(new File("res/textures/launcher.png")).getScaledInstance(mainFrame.getWidth()-10, mainFrame.getHeight()-20, Image.SCALE_FAST);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mainPanel.setOpaque(false);
        mainFrame.add(new App());
        mainFrame.setResizable(true);
        mainFrame.addComponentListener(new ComponentListener() {
            public void componentResized(ComponentEvent e) {
                try {
                    bg = ImageIO.read(new File("res/textures/launcher.png")).getScaledInstance(mainFrame.getWidth()-10, mainFrame.getHeight()-20, Image.SCALE_FAST);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            public void componentMoved(ComponentEvent e) {}
            public void componentShown(ComponentEvent e) {}
            public void componentHidden(ComponentEvent e) {}
        });
        
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);

        /*
        Manager gameWindow = new Manager();
        gameWindow.createGameWindow(1280,720, "Cave Game Launcher", "game");
        */
    }
}
