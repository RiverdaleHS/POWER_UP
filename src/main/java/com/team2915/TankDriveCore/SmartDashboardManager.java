package com.team2915.TankDriveCore;

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
        SmartDashboard.putNumber("NAVX Angle", Robot.driveTrain.getHeading());
    }
}