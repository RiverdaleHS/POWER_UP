package com.team2915.POWER_UP;

import com.team2915.POWER_UP.commands.ExecuteTrajectory;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * Created by Henry on 5/10/17.
 */
public class SmartDashboardManager {

    //Camera
    UsbCamera cam0;
    CvSink cvsink0;
    VideoSink server;

    //Stats for Profiling in meters
    public double wheel_base_width = 0.635;
    public int encoder_ticks_per_rev = 1920;
    public double velocity_feedforward = 0.5; // m/s
    public double max_velocity = 4;
    public double max_acceleration = 0.5; // m/s^2
    public double max_jerk = 60;// m/s^3 ?
    public double wheel_diameter = 0.1651;
    public double proportional_gain = 0.8;
    public double derivative_gain = 0.0;
    public double acceleration_feedforward = -0.1;
    public double velocity_intercept = 0.0;
    public double turn_gain = -0.01;


    public SmartDashboardManager() {
        //Camera
        cam0 = CameraServer.getInstance().startAutomaticCapture(0);
        server = CameraServer.getInstance().getServer();
        cvsink0 = new CvSink("cam0cv");

        cvsink0.setSource(cam0);
        cvsink0.setEnabled(true);
        //Command Buttons
        SmartDashboard.putData("Execute Trajectory", new ExecuteTrajectory());
        //Numbers used in trajectory tracking
        SmartDashboard.putNumber("Max Velocity (m/s)*", 9);
        SmartDashboard.putNumber("Max Acceleration (m/s/s)*", 2);
        SmartDashboard.putNumber("Max Jerk (m/s/s/s)*", 60);
        //Numbers used in trajectory tracking
        SmartDashboard.putNumber("Wheel Diameter (m)", 0.1651);
        SmartDashboard.putNumber("Proportional (Gain)", 1.4);
        SmartDashboard.putNumber("Derivative (Gain)", 0.3);
        SmartDashboard.putNumber("Acceleration (Gain)", 0.0);
        SmartDashboard.putNumber("Wheelbase (m)", 0.653);
        SmartDashboard.putNumber("Velocity Intercept (output)", 0.05);
        SmartDashboard.putNumber("Turn Gain", -0.01);
    }

    public void updateSmartDashboard() {
        //Numbers used in trajectory tracking
        proportional_gain = SmartDashboard.getNumber("MP Proportional (Gain)", 1.4);
        derivative_gain = SmartDashboard.getNumber("Derivative (Gain)", 0.3);
        velocity_feedforward = SmartDashboard.getNumber("Velocity Feedforward", 9);
        acceleration_feedforward = SmartDashboard.getNumber("Acceleration Feedforward", 0.0);
        wheel_base_width = SmartDashboard.getNumber("Wheelbase (m)", 0.653);
        wheel_diameter = SmartDashboard.getNumber("Wheel Diameter (m)", 0.1651);

        velocity_intercept = SmartDashboard.getNumber("Velocity Intercept (output)", 0.05); // Maybe dont use
        //Numbers used in trajectory generation
        max_velocity = SmartDashboard.getNumber("Max Velocity", 9);
        max_acceleration = SmartDashboard.getNumber("Max Acceleration", 2);
        max_jerk = SmartDashboard.getNumber("Max Jerk", 60);






        turn_gain = SmartDashboard.getNumber("Turn Gain", -0.01);

//        //Elevator Encoder Output, One Way
//        SmartDashboard.putNumber("MM Sensor Position", Robot.elevator.getLeftEncoderPosition());
//        SmartDashboard.putNumber("MM Sensor Velocity", Robot.elevator.getLeftEncoderVelocity());
//        SmartDashboard.putNumber("MM Active Trajectory Position", Robot.elevator.getActiveTrajectoryPosition());
//        SmartDashboard.putNumber("MM Active Trajectory Velocity", Robot.elevator.getActiveTrajectoryVelocity());
//        SmartDashboard.putNumber("MM Closed Loop Error", Robot.elevator.getClosedLoopError());
//        SmartDashboard.putNumber("MM Motor Output", Robot.elevator.getMotorOutput());

        //Sensors
        SmartDashboard.putNumber("NavX Angle", Robot.chassis.getHeading());
        SmartDashboard.putNumber("Left Encoder", Robot.chassis.getLeftEncoder());
        SmartDashboard.putNumber("Left Encoder Rate", Robot.chassis.getLeftEncoderRate());
        SmartDashboard.putNumber("Right Encoder", Robot.chassis.getRightEncoder());

        SmartDashboard.putData(Robot.chassis);
        SmartDashboard.putData(Robot.elevator);
        SmartDashboard.putData(Robot.climber);
    }
}
