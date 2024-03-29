package com.team2915.POWER_UP.commands;

import com.team254.frc2016.CheesyDriveHelper;
import com.team254.lib.util.DriveSignal;
import com.team2915.POWER_UP.Robot;
import com.team2915.POWER_UP.RobotMap;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Created by Henry on 5/4/17.
 */
public class DriveOpenLoop extends Command {

    CheesyDriveHelper cheesyDriveHelper = new CheesyDriveHelper();

    public DriveOpenLoop(){
        requires(Robot.chassis);
    }


    @Override
    protected void execute() {
        super.execute();
        double throttle = Robot.io.getXbox().getRawAxis(RobotMap.ControlsMap.throttleAxis);
        double turn = Robot.io.getXbox().getRawAxis(RobotMap.ControlsMap.turnAxis);
        boolean quickTurn = Robot.io.getXbox().getRawButton(RobotMap.ControlsMap.quickTurn);

        //Testing turn in place purposes only!
        if (quickTurn) {
            throttle = 0;
        }


        DriveSignal driveSignal = cheesyDriveHelper.cheesyDrive(throttle, -turn, quickTurn);
        Robot.chassis.setSpeed(driveSignal.leftMotor, driveSignal.rightMotor);
        System.out.println("Left: " + driveSignal.leftMotor + " Right: " + driveSignal.rightMotor);

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
