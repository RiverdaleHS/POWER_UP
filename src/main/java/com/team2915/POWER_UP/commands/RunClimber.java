package com.team2915.POWER_UP.commands;

import com.team2915.POWER_UP.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class RunClimber extends Command {
    public RunClimber(){
        requires(Robot.climber);
    }

    @Override
    protected void execute() {
        if (Robot.io.getXbox().getRawButton(1)){
            Robot.climber.setMotor(-0.8);
        }else{
            Robot.climber.setMotor(0);
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
