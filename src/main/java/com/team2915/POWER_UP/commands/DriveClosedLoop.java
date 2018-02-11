package com.team2915.POWER_UP.commands;

import com.team254.frc2016.CheesyDriveHelper;
import com.team2915.POWER_UP.Robot;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

//Abandoning for now.  I think this is rather hard without SRX encoders on the chassis like I wanted

public class DriveClosedLoop extends Command {

    CheesyDriveHelper cheesyDriveHelper = new CheesyDriveHelper();

    PIDController leftPID;

    boolean hasRun = false;

    public DriveClosedLoop(){
        requires(Robot.chassis);
    }

    public void execute() {
        super.execute();
        if (hasRun == false){
            hasRun = true;
           // leftPID = new PIDController(Robot.smartDashboardManager.left_P, Robot.smartDashboardManager.left_I, Robot.smartDashboardManager.left_D, Robot.chassis.getLeftEncoder(), )
        }

    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void interrupted() {
        super.interrupted();
        end();
    }

    @Override
    protected void end() {
        super.end();
        Robot.chassis.stop();
    }
}
