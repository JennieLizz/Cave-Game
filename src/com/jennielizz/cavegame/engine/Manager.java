package com.jennielizz.cavegame.engine;

import java.io.IOException;

import com.jennielizz.cavegame.display.Display;

public class Manager {

    public static int width, height;
    public static String title, launchArguments;

    public void createGameWindow(int width, int height, String title, String launchArguments) {
        System.out.println("Manager created!");

        this.width = width;
        this.height = height;
        this.title = title;
        this.launchArguments = launchArguments;

        Display display = new Display(width, height, title, launchArguments);

        display.beginUpdateLoop();

        display.destroy();

        System.out.println("Manager destroyed!");
    }
}
