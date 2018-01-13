package com.team2915.TankDriveCore.subsystems;


import com.ctre.MotorControl.CANTalon;
import com.kauailabs.navx.frc.AHRS;
import com.team2915.TankDriveCore.RobotMap;
import com.team2915.TankDriveCore.commands.DriveWithXbox;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 * Created by Henry on 5/2/17.
 */
public class DriveTrain extends Subsystem {

    private AHRS ahrs = new AHRS(RobotMap.DriveTrainMap.ahrs);

    private CANTalon leftMaster = new CANTalon(RobotMap.DriveTrainMap.leftMaster);
    private CANTalon leftSlave = new CANTalon(RobotMap.DriveTrainMap.leftSlave);
    private CANTalon rightMaster = new CANTalon(RobotMap.DriveTrainMap.rightMaster);
    private CANTalon rightSlave = new CANTalon(RobotMap.DriveTrainMap.rightSlave);

    private Jaguar jagl = new Jaguar(0);
    private Jaguar jagr = new Jaguar(1);

    public DriveTrain() {
        //set slaves to follow their masters
        leftSlave.changeControlMode(CANTalon.TalonControlMode.Follower);
        leftSlave.set(leftMaster.getDeviceID());
        rightSlave.changeControlMode(CANTalon.TalonControlMode.Follower);
        rightSlave.set(rightMaster.getDeviceID());
        //Invert
        leftMaster.setInverted(true);
    }


    @Override
    protected void initDefaultCommand() {

        //XBOX Drive!!!!!!
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

    public void setAux(double l, double r){
        jagl.set(l);
        jagr.set(r);
    }

    public double getHeading(){
        return ahrs.pidGet();
    }
}
