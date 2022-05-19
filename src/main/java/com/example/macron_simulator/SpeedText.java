package com.example.macron_simulator;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class SpeedText extends Text {

    public SpeedText(String speed)  {

        super();

        this.setFont(new Font(30));

        this.setText("You slapped " + "at " + speed + " km/hr");

    }


}
