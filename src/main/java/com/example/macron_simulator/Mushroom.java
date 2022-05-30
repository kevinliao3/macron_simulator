package com.example.macron_simulator;

public class Mushroom extends Item {

    public Mushroom() {
        super("mushroom");
    }

    @Override
    public void effect() {
        Main.curScene.currentSlapScene.slowSpeed(10);
    }


}
