package com.jennielizz.cavegame.engine;

import java.io.IOException;

import com.jennielizz.cavegame.display.Display;

public class Manager {

    public static void createGameWindow(int width, int height, String title) {
        System.out.println("Manager created!");

        Display display = new Display(width, height, title);

        display.beginUpdateLoop();

        display.destroy();

        System.out.println("Manager destroyed!");
    }

    public static void createLauncherWindow(int width, int height, String title) {
        System.out.println("Manager created!");

        Display display = new Display(width, height, title);

        display.beginUpdateLoop();

        display.destroy();

        System.out.println("Manager destroyed!");
    }

}
