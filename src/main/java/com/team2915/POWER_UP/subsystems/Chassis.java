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
    private TalonSRX leftSlaveA = new TalonSRX(RobotMap.ChassisMap.leftSlave);

    private TalonSRX rightMaster = new TalonSRX(RobotMap.ChassisMap.rightMaster);
    private TalonSRX rightSlaveA = new TalonSRX(RobotMap.ChassisMap.rightSlave);

    private Encoder leftEncoder = new Encoder(RobotMap.ChassisMap.leftEncoderTop, RobotMap.ChassisMap.leftEncoderBottom);
    private Encoder rightEncoder = new Encoder(RobotMap.ChassisMap.rightEncoderTop, RobotMap.ChassisMap.rightEncoderBottom);

    public Chassis(){
        //Configure Masters

        rightMaster.setNeutralMode(NeutralMode.Brake);

        leftMaster.setNeutralMode(NeutralMode.Brake);
        //Configure left slaves
        leftSlaveA.set(ControlMode.Follower, leftMaster.getDeviceID());

        leftSlaveA.setNeutralMode(NeutralMode.Brake);
        leftMaster.setInverted(true);
        leftSlaveA.setInverted(true);


        //Configure right slaves
        rightSlaveA.set(ControlMode.Follower, rightMaster.getDeviceID());

        rightSlaveA.setNeutralMode(NeutralMode.Brake);



        //Configure Sensors
        //leftEncoder.reset();
        //rightEncoder.reset();
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

    public int getLeftEncoder() {
        return leftEncoder.get();
    }

    public double getLeftEncoderRate() {
        return leftEncoder.getRate();
    }

    public double getRightEncoderRate(){
        return rightEncoder.getRate();
    }

    public int getRightEncoder() {
        return rightEncoder.get();
    }

    public double getAverageLeftVoltage(){
        return (leftMaster.getMotorOutputVoltage() + leftSlaveA.getMotorOutputVoltage())/3;
    }

    public double getAverageRightVoltage(){
        return (rightMaster.getMotorOutputVoltage() + rightSlaveA.getMotorOutputVoltage())/2;
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
