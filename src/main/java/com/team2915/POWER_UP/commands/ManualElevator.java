package com.team2915.POWER_UP.commands;

import com.team2915.POWER_UP.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ManualElevator extends Command {
    public ManualElevator(){
        requires(Robot.elevator);
    }

    protected void execute() {
        if (Robot.io.getXbox().getRawButton(3)){
            Robot.elevator.setPercentOutput(1);
        }else if (Robot.io.getXbox().getRawButton(2)){
            Robot.elevator.setPercentOutput(-0.3);
        } else {
            Robot.elevator.setPercentOutput(0);
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
        Robot.elevator.setPercentOutput(0);
    }
}
