package com.team2915.POWER_UP.subsystems;

import com.team2915.POWER_UP.commands.RunClimber;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem{
    private Spark motor = new Spark(0);

    public Climber(){

    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new RunClimber());
    }

    public void setMotor(double speed){
        motor.set(speed);
    }
}
