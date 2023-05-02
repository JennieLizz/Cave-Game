package com.jennielizz.cavegame.engine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OBJLoader {

    public static RawModel loadObjModel(String path, TextureLoader loader) {
        try {
            FileReader file = new FileReader(new File(path));
        } catch (FileNotFoundException e) {
            System.err.println("Couldn't load file!");
            e.printStackTrace();
            System.exit(-1);
        }   
    }   
}
