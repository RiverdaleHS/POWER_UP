package com.team2915.POWER_UP.commands;

import com.team2915.POWER_UP.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class TurnToTarget extends Command {

    public TurnToTarget() {
        requires(Robot.jetson);
    }


    @Override
    protected void execute() {
        super.execute();

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
    }
}
