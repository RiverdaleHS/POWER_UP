package com.team2915.POWER_UP.commands;


import com.team2915.POWER_UP.Robot;
import com.team2915.POWER_UP.RobotMap;
import com.team2915.POWER_UP.util.CSVUtilities;
import edu.wpi.first.wpilibj.command.Command;

import java.io.IOException;
import java.util.Date;

public class Characterization extends Command {

    private double speed = 0;

    public Characterization(){
        requires(Robot.chassis);
        try {
            CSVUtilities.logCSVLine("MPC.csv", "avgLeftOutputVoltage, avgRightOutputVoltage, leftEncoderRate, rightEncoderRate, timestamp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void execute() {
        super.execute();
        if (Robot.io.getXbox().getRawButton(RobotMap.ControlsMap.characterizationIncreaseOutput)){
            speed += 0.05;
        }
        if (Robot.io.getXbox().getRawButton(RobotMap.ControlsMap.characterizationDecreaseOutput)){
            speed -= 0.05;
        }
        if (Robot.io.getXbox().getRawButton(RobotMap.ControlsMap.characterizationSavePoint)){
            //TODO: get voltage output from talons instead of percent?
            try {
                CSVUtilities.logCSVLine("MPC.csv", Robot.chassis.getAverageLeftVoltage() + " , " + Robot.chassis.getAverageRightVoltage() + " , " + Robot.chassis.getLeftEncoderRate() + " , " + Robot.chassis.getRightEncoderRate() + " , " + new Date());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Robot.chassis.setSpeed(speed, speed);
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
