package com.team2915.POWER_UP.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.*;
import com.team2915.POWER_UP.RobotMap;
import com.team2915.POWER_UP.commands.DriveWithXbox;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Created by Henry on 5/2/17.
 */
public class Chassis extends Subsystem {

    private TalonSRX leftDrive;
    private TalonSRX rightDrive;

    private DoubleSolenoid shifter;



    public Chassis() {
        leftDrive = RobotMap.leftDrive;
        rightDrive = RobotMap.rightDrive;
        shifter = RobotMap.shifter;
    }


    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new DriveWithXbox());
    }

    public void setSpeed(double left, double right){
        leftDrive.set(ControlMode.PercentOutput, left);
        rightDrive.set(ControlMode.PercentOutput, -right);
    }

    public void stop(){
        leftDrive.set(ControlMode.PercentOutput, 0);
        rightDrive.set(ControlMode.PercentOutput, 0);
    }

    public double getHeading(){
        return RobotMap.navx.pidGet();
    }

    public void shiftLow(){
        shifter.set(DoubleSolenoid.Value.kForward);
    }
    public void shiftHigh(){
        shifter.set(DoubleSolenoid.Value.kReverse);
    }
    public void shiftOff(){
        shifter.set(DoubleSolenoid.Value.kOff);
    }
}
