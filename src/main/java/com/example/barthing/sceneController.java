package com.example.barthing;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.HashMap;

public class sceneController {
    private HashMap<String, Pane> screenMap = new HashMap<>();
    private Scene main;

    public sceneController(Scene main) {
        this.main = main;
    }

    protected void addScreen(String name, Pane pane){
        screenMap.put(name, pane);
    }

    protected void removeScreen(String name){
        screenMap.remove(name);
    }

    protected void activate(String name){
        main.setRoot( screenMap.get(name) );
    }
}
