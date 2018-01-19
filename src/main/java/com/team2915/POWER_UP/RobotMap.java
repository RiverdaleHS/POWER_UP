package com.team2915.POWER_UP;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SPI;

import java.util.ArrayList;

/**
 * Created by Henry on 5/2/17.
 */
public class RobotMap {

    private static Boolean isCompRobot = true;

    //Chassis
    //NavX
    public static AHRS navx;
    //Left SRXs
    public static TalonSRX leftDrive;
    private static TalonSRX leftMaster;
    private static ArrayList<TalonSRX> leftSlaves;
    //Right SRXs
    public static TalonSRX rightDrive;
    private static TalonSRX rightMaster;
    private static ArrayList<TalonSRX> rightSlaves;
    //Shifter
    public static DoubleSolenoid shifter;
    //PDP
    public static PowerDistributionPanel pdp;
    //Compressor
    public static Compressor compressor;

    public static void init(){
        //Chassis
        navx = new AHRS(SPI.Port.kMXP);
        //Initialize Master SRXs
        leftMaster = new TalonSRX(30);
        rightMaster = new TalonSRX(20);
        //Initialize Slave ArrayLists
        leftSlaves = new ArrayList<TalonSRX>();
        rightSlaves = new ArrayList<TalonSRX>();
        //Add First Slavee
        leftSlaves.add(new TalonSRX(31));
        rightSlaves.add(new TalonSRX(21));
        //Add Second Slaves if on test chassis
        if (isCompRobot){
            leftSlaves.add(new TalonSRX(32));
            rightSlaves.add(new TalonSRX(22));
        }
        //Configure left slaves
        for (TalonSRX ls : leftSlaves) {
            ls.set(ControlMode.Follower, leftMaster.getDeviceID());
        }
        //Configure right slaves
        for (TalonSRX rs : rightSlaves) {
            rs.set(ControlMode.Follower, rightMaster.getDeviceID());
        }
        //set public drives to masters to simplify
        leftDrive = leftMaster;
        rightDrive = rightMaster;
        //Initialize Shifter
        shifter = new DoubleSolenoid(0,1);
        //PDP
        pdp = new PowerDistributionPanel(0);
        compressor = new Compressor();

    }

}
