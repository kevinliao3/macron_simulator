package com.example.macron_simulator;

import static com.example.macron_simulator.Main.curScene;

public class Baguette extends Item{

    public Baguette() {
        super("baguette");
    }

    @Override
    public void effect() {
        Main.curScene.increaseHealth(40.0);
    }
}
