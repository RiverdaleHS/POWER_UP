package com.team2915.POWER_UP;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Created by Henry on 5/10/17.
 */
public class SmartDashboardManager {

    public SmartDashboardManager() {
    }

    public void updateSmartDashboard() {
        SmartDashboard.putNumber("PDP Voltage", RobotMap.pdp.getVoltage());
        SmartDashboard.putNumber("NavX Angle", Robot.chassis.getHeading());
        SmartDashboard.putNumber("PDP Temperature", RobotMap.pdp.getTemperature());
        SmartDashboard.putNumber("Compressor Current", RobotMap.compressor.getCompressorCurrent());
        //SmartDashboard.putNumber("PDP Current", RobotMap.pdp.getCurrent()); use for individual channels

    }
}