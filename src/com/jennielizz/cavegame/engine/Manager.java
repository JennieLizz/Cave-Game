package com.jennielizz.cavegame.engine;

import com.jennielizz.cavegame.display.Display;

public class Manager {

    int width = 1280, height = 720;
    String title = "Cave Game";

    public Manager() {
        System.out.println("Manager created!");

        Display display = new Display(width, height, title);

        display.BeginUpdateLoop();

        display.Destroy();

        System.out.println("Manager destroyed!");
    }

}
