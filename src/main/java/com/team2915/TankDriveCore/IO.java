package com.team2915.TankDriveCore;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Created by Henry on 5/2/17.
 */
public class IO {

    private Joystick xbox = new Joystick(0);

    public IO(){

    }

    public Joystick getXbox(){
        return xbox;
    }
}
