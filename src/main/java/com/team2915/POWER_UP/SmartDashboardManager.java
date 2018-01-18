package com.team2915.POWER_UP;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Created by Henry on 5/10/17.
 */
public class SmartDashboardManager {

    PowerDistributionPanel pdp = new PowerDistributionPanel();

    public SmartDashboardManager() {
    }

    public void updateSmartDashboard() {
        SmartDashboard.putNumber("PDP Voltage", pdp.getVoltage());
        SmartDashboard.putNumber("NAVX Angle", Robot.chassis.getHeading());
        SmartDashboard.putNumber("Left Encoder", Robot.chassis.getLeftEncoderDistance());
        SmartDashboard.putNumber("Right Encoder", Robot.chassis.getRightEncoderDistance());
        SmartDashboard.putNumber("Acceleration", Robot.chassis.getAcceleration());
        //Subsystems to track commands
        SmartDashboard.putData(Robot.chassis);
    }
}