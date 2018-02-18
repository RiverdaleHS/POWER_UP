package com.team2915.POWER_UP;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Created by Henry on 5/2/17.
 */
public class IO {

    private Joystick xbox = new Joystick(0);
//    Button mp = new JoystickButton(xbox, RobotMap.ControlsMap.executeTrajectory);
//
//    public IO(){
//        mp.whenPressed(new ExecuteTrajectory());
//    }

    public Joystick getXbox(){
        return xbox;
    }
}
