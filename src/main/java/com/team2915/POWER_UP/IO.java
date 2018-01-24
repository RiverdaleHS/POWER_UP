package com.team2915.POWER_UP;

import com.team2915.POWER_UP.TrajectoryGeneration.TrajectoryGenerator;
import com.team2915.POWER_UP.commands.ExecuteTrajectory;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import jaci.pathfinder.Trajectory;

import java.awt.*;

/**
 * Created by Henry on 5/2/17.
 */
public class IO {

    private XboxController xbox = new XboxController(0);
    Button mp = new JoystickButton(xbox, 2);

    public IO(){

        mp.whenPressed(new ExecuteTrajectory());
    }

    public XboxController getXbox(){
        return xbox;
    }
}
