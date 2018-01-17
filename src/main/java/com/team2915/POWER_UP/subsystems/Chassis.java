package com.team2915.POWER_UP.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.*;
import com.kauailabs.navx.frc.AHRS;
import com.team2915.POWER_UP.RobotMap;
import com.team2915.POWER_UP.commands.DriveWithXbox;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
//import pathfinder

/**
 * Created by Henry on 5/2/17.
 */
public class Chassis extends Subsystem {

    private AHRS ahrs = new AHRS(RobotMap.DriveTrainMap.ahrs);

    private TalonSRX leftMaster = new TalonSRX(RobotMap.DriveTrainMap.leftMaster);
    private TalonSRX leftSlaveA = new TalonSRX(RobotMap.DriveTrainMap.leftSlaveA);
    private TalonSRX leftSlaveB = new TalonSRX(RobotMap.DriveTrainMap.leftSlaveB);
    private TalonSRX rightMaster = new TalonSRX(RobotMap.DriveTrainMap.rightMaster);
    private TalonSRX rightSlaveA = new TalonSRX(RobotMap.DriveTrainMap.rightSlaveA);
    private TalonSRX rightSlaveB = new TalonSRX(RobotMap.DriveTrainMap.rightSlaveB);

    private DoubleSolenoid shifter = new DoubleSolenoid(RobotMap.DriveTrainMap.shifterA, RobotMap.DriveTrainMap.shifterB);

    public Chassis() {
        //set slaves to follow their masters
        leftSlaveA.set(ControlMode.Follower, RobotMap.DriveTrainMap.leftMaster);
        leftSlaveB.set(ControlMode.Follower, RobotMap.DriveTrainMap.leftMaster);

        rightSlaveA.set(ControlMode.Follower, RobotMap.DriveTrainMap.rightMaster);
        rightSlaveB.set(ControlMode.Follower, RobotMap.DriveTrainMap.rightMaster);
        //Invert
        //leftMaster.setInverted(true);
    }


    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new DriveWithXbox());
    }

    public void setSpeed(double left, double right){
        leftMaster.set(ControlMode.PercentOutput, left);
        rightMaster.set(ControlMode.PercentOutput, right);
    }

    public void stop(){
        leftMaster.set(ControlMode.PercentOutput, 0);
        rightMaster.set(ControlMode.PercentOutput, 0);
    }


    public double getHeading(){
        return ahrs.pidGet();
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
