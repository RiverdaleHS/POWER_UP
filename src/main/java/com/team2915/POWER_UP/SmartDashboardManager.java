package com.team2915.POWER_UP;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Created by Henry on 5/10/17.
 */
public class SmartDashboardManager {

    public SmartDashboardManager() {
    }

    public void updateSmartDashboard() {
        SmartDashboard.putNumber("NavX Angle", Robot.chassis.getHeading());
        SmartDashboard.putNumber("Left Encoder", Robot.chassis.getLeftEncoder());
        SmartDashboard.putNumber("Right Encoder", Robot.chassis.getRightEncoder());
    }
}