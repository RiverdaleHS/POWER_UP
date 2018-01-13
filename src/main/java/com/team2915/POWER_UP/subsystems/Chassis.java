package com.team2915.POWER_UP.subsystems;


import com.ctre.MotorControl.CANTalon;
import com.kauailabs.navx.frc.AHRS;
import com.team2915.POWER_UP.RobotMap;
import com.team2915.POWER_UP.commands.DriveWithXbox;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 * Created by Henry on 5/2/17.
 */
public class Chassis extends Subsystem {

    private AHRS ahrs = new AHRS(RobotMap.DriveTrainMap.ahrs);

    private CANTalon leftMaster = new CANTalon(RobotMap.DriveTrainMap.leftMaster);
    private CANTalon leftSlaveA = new CANTalon(RobotMap.DriveTrainMap.leftSlaveA);
    private CANTalon leftSlaveB = new CANTalon(RobotMap.DriveTrainMap.leftSlaveB);
    private CANTalon rightMaster = new CANTalon(RobotMap.DriveTrainMap.rightMaster);
    private CANTalon rightSlaveA = new CANTalon(RobotMap.DriveTrainMap.rightSlaveA);
    private CANTalon rightSlaveB = new CANTalon(RobotMap.DriveTrainMap.rightSlaveB);

    public Chassis() {
        //set slaves to follow their masters
        leftSlaveA.changeControlMode(CANTalon.TalonControlMode.Follower);
        leftSlaveA.set(leftMaster.getDeviceID());
        leftSlaveB.changeControlMode(CANTalon.TalonControlMode.Follower);
        leftSlaveB.set(leftMaster.getDeviceID());
        rightSlaveA.changeControlMode(CANTalon.TalonControlMode.Follower);
        rightSlaveA.set(rightMaster.getDeviceID());
        rightSlaveB.changeControlMode(CANTalon.TalonControlMode.Follower);
        rightSlaveB.set(rightMaster.getDeviceID());
        //Invert
        leftMaster.setInverted(true);
    }


    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new DriveWithXbox());
    }

    public void setSpeed(double left, double right){
        leftMaster.set(left);
        rightMaster.set(right);
    }

    public void stop(){
        leftMaster.set(0);
        rightMaster.set(0);
    }


    public double getHeading(){
        return ahrs.pidGet();
    }
}
