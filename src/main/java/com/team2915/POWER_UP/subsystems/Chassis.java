package com.team2915.POWER_UP.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.*;
import com.kauailabs.navx.frc.AHRS;
import com.team2915.POWER_UP.commands.DriveWithXbox;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.team2915.POWER_UP.Robot;
import java.util.ArrayList;

/**
 * Created by Henry on 5/2/17.
 */
public class Chassis extends Subsystem {
    private AHRS navx = new AHRS(SPI.Port.kMXP);
    private TalonSRX leftMaster;
    private ArrayList<TalonSRX> leftSlaves;
    private TalonSRX rightMaster;
    private ArrayList<TalonSRX> rightSlaves;
    //Shifter
    private DoubleSolenoid shifter = new DoubleSolenoid(0,1);

    private Encoder leftEncoder = new Encoder(0,1);
    private Encoder rightEncoder = new Encoder(2,3);

    public Chassis(){
        //Chassis

        //Initialize Master SRXs
        leftMaster = new TalonSRX(30);
        rightMaster = new TalonSRX(20);
        //Initialize Slave ArrayLists
        leftSlaves = new ArrayList<TalonSRX>();
        rightSlaves = new ArrayList<TalonSRX>();
        //Add First Slave
        leftSlaves.add(new TalonSRX(31));
        rightSlaves.add(new TalonSRX(21));
        //Add Second Slaves if on test chassis
        if (Robot.isCompBot){
            leftSlaves.add(new TalonSRX(32));
            rightSlaves.add(new TalonSRX(22));
        }

        rightMaster.setInverted(true);
        leftMaster.setNeutralMode(NeutralMode.Brake);
        rightMaster.setNeutralMode(NeutralMode.Brake);
        //Configure left slaves
        for (TalonSRX ls : leftSlaves) {
            ls.set(ControlMode.Follower, leftMaster.getDeviceID());

            ls.setNeutralMode(NeutralMode.Brake);
        }
        //Configure right slaves
        for (TalonSRX rs : rightSlaves) {
            rs.set(ControlMode.Follower, rightMaster.getDeviceID());
            rs.setNeutralMode(NeutralMode.Brake);
            rs.setInverted(true);
        }
        //set public drives to masters to simplify


        //Initialize Shifter

        leftEncoder.reset();
        rightEncoder.reset();
        navx.reset();
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
        return navx.pidGet();
    }
    public float getAcceleration() {
        return navx.getRawAccelY();
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

    public int getLeftEncoder() {
        return leftEncoder.get();
    }

    public int getRightEncoder() {
        return rightEncoder.get();
    }
}
