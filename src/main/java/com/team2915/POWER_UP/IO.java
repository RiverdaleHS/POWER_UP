package com.team2915.POWER_UP;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

/**
 * Created by Henry on 5/2/17.
 */
public class IO {

    private XboxController xbox = new XboxController(0);

    public IO(){

    }

    public XboxController getXbox(){
        return xbox;
    }
}
