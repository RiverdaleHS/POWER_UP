package com.team2915.POWER_UP.commands;

import com.team254.frc2016.CheesyDriveHelper;
import com.team254.lib.util.DriveSignal;
import com.team2915.POWER_UP.Robot;
import com.team2915.POWER_UP.subsystems.Chassis;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Created by Henry on 5/4/17.
 */
public class DriveWithXbox extends Command {

    CheesyDriveHelper cheesyDriveHelper = new CheesyDriveHelper();

    public DriveWithXbox(){
        requires(Robot.driveTrain);
    }


    @Override
    protected void execute() {
        super.execute();
        double throttle = Robot.io.getXbox().getRawAxis(1);
        double turn = -Robot.io.getXbox().getRawAxis(4);
        boolean quickTurn = Robot.io.getXbox().getRawButton(6);


        DriveSignal driveSignal = cheesyDriveHelper.cheesyDrive(throttle, turn, quickTurn);

        Robot.driveTrain.setSpeed(driveSignal.leftMotor, driveSignal.rightMotor);

        if (Robot.io.getXbox().getRawButton(2)){
            Robot.driveTrain.setShifterOFF();
        }

        if (Robot.io.getXbox().getRawButton(3)){
            Robot.driveTrain.setShifterForward();
        }

        if (Robot.io.getXbox().getRawButton(0)){
            Robot.driveTrain.setShifterBackward();
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
        Robot.driveTrain.stop();
    }
}
