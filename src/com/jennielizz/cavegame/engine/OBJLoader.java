package com.jennielizz.cavegame.engine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.jennielizz.cavegame.display.Loader;
import com.jennielizz.cavegame.display.RawModel;

public class OBJLoader {

    public static RawModel loadObjModel(String path, Loader loader) {
        try {
            FileReader file = new FileReader(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("Couldn't load file!");
        }
        
    }
    
}
