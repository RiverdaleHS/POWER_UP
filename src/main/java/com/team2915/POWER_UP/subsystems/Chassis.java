package com.team2915.POWER_UP.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.*;
import com.kauailabs.navx.frc.AHRS;
import com.team2915.POWER_UP.RobotMap;
import com.team2915.POWER_UP.commands.DriveOpenLoop;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Created by Henry on 5/2/17.
 */
public class Chassis extends Subsystem {
    private AHRS navx = new AHRS(RobotMap.ChassisMap.ahrs);

    private TalonSRX leftMaster = new TalonSRX(RobotMap.ChassisMap.leftMaster);
    private TalonSRX leftSlaveA = new TalonSRX(RobotMap.ChassisMap.leftSlaveA);
    private TalonSRX leftSlaveB = new TalonSRX(RobotMap.ChassisMap.leftSlaveB);

    private TalonSRX rightMaster = new TalonSRX(RobotMap.ChassisMap.rightMaster);
    private TalonSRX rightSlaveA = new TalonSRX(RobotMap.ChassisMap.rightSlaveA);
    private TalonSRX rightSlaveB = new TalonSRX(RobotMap.ChassisMap.rightSlaveB);
    //Shifter
    private DoubleSolenoid shifter = new DoubleSolenoid(RobotMap.ChassisMap.shifterA, RobotMap.ChassisMap.shifterB);

    private Encoder leftEncoder = new Encoder(RobotMap.ChassisMap.leftEncoderTop, RobotMap.ChassisMap.leftEncoderBottom);
    private Encoder rightEncoder = new Encoder(RobotMap.ChassisMap.rightEncoderTop, RobotMap.ChassisMap.rightEncoderBottom);

    public Chassis(){
        //Configure Masters
        rightMaster.setInverted(true);
        rightMaster.setNeutralMode(NeutralMode.Brake);
        leftMaster.setInverted(false);
        leftMaster.setNeutralMode(NeutralMode.Brake);
        //Configure left slaves
        leftSlaveA.set(ControlMode.Follower, leftMaster.getDeviceID());
        leftSlaveB.set(ControlMode.Follower, leftMaster.getDeviceID());
        leftSlaveA.setNeutralMode(NeutralMode.Brake);
        leftSlaveB.setNeutralMode(NeutralMode.Brake);
        leftSlaveA.setInverted(false);
        leftSlaveB.setInverted(false);
        //Configure right slaves
        rightSlaveA.set(ControlMode.Follower, rightMaster.getDeviceID());
        rightSlaveB.set(ControlMode.Follower, rightMaster.getDeviceID());
        rightSlaveA.setNeutralMode(NeutralMode.Brake);
        rightSlaveB.setNeutralMode(NeutralMode.Brake);
        rightSlaveA.setInverted(true);
        rightSlaveB.setInverted(true);
        //Configure Sensors
        leftEncoder.reset();
        rightEncoder.reset();
        navx.reset();
    }


    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new DriveOpenLoop());
    }

    public void setSpeed(double left, double right){
        leftMaster.set(ControlMode.PercentOutput, left);
        rightMaster.set(ControlMode.PercentOutput, right);
    }

    public void stop(){
        leftMaster.set(ControlMode.PercentOutput, 0);
        rightMaster.set(ControlMode.PercentOutput, 0);
    }


    public void shiftLow(){
        shifter.set(DoubleSolenoid.Value.kForward);
    }
    public void shiftHigh(){
        shifter.set(DoubleSolenoid.Value.kReverse);
    }

    public int getLeftEncoder() {
        return leftEncoder.get();
    }

    public double getLeftEncoderRate() {
        return leftEncoder.getRate();
    }

    public int getRightEncoder() {
        return rightEncoder.get();
    }

    public double getHeading(){
        return navx.pidGet();
    }

    public void zeroNavX(){
        navx.zeroYaw();
    }

    public float getAcceleration() {
        return navx.getRawAccelY();
        //TODO: This is in here for collision detection
    }
}
