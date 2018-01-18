package com.team2915.POWER_UP.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.*;
import com.kauailabs.navx.frc.AHRS;
import com.team2915.POWER_UP.RobotMap;
import com.team2915.POWER_UP.commands.DriveWithXbox;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
//import pathfinder

/**
 * Created by Henry on 5/2/17.
 */
public class Chassis extends Subsystem {

    private AHRS ahrs = new AHRS(RobotMap.ChassisMap.ahrs);

    private TalonSRX leftMaster = new TalonSRX(RobotMap.ChassisMap.leftMaster);
    private TalonSRX leftSlaveA = new TalonSRX(RobotMap.ChassisMap.leftSlaveA);
    private TalonSRX leftSlaveB = new TalonSRX(RobotMap.ChassisMap.leftSlaveB);
    private TalonSRX rightMaster = new TalonSRX(RobotMap.ChassisMap.rightMaster);
    private TalonSRX rightSlaveA = new TalonSRX(RobotMap.ChassisMap.rightSlaveA);
    private TalonSRX rightSlaveB = new TalonSRX(RobotMap.ChassisMap.rightSlaveB);

    private Encoder leftEncoder = new Encoder(RobotMap.ChassisMap.leftEncoderTop, RobotMap.ChassisMap.leftEncoderBottom);
    private Encoder rightEncoder = new Encoder(RobotMap.ChassisMap.rightEncoderTop, RobotMap.ChassisMap.rightEncoderBottom);

    private DoubleSolenoid shifter = new DoubleSolenoid(RobotMap.ChassisMap.shifterA, RobotMap.ChassisMap.shifterB);

    public Chassis() {
        //set slaves to follow their masters
        leftSlaveA.set(ControlMode.Follower, RobotMap.ChassisMap.leftMaster);
        leftSlaveB.set(ControlMode.Follower, RobotMap.ChassisMap.leftMaster);

        rightSlaveA.set(ControlMode.Follower, RobotMap.ChassisMap.rightMaster);
        rightSlaveB.set(ControlMode.Follower, RobotMap.ChassisMap.rightMaster);

        //Reset Encoders
        leftEncoder.reset();
        rightEncoder.reset();
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
    public float getAcceleration() {
        return ahrs.getRawAccelY();
    }

    public int getLeftEncoderDistance() {
        return leftEncoder.get();
    }

    public int getRightEncoderDistance() {
        return rightEncoder.get();
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
