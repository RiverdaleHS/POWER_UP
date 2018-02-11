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
    public double max_velocity = 0.5; // m/s
    public double max_acceleration = 0.5; // m/s^2
    public double max_jerk = 60;// m/s^3 ?
    public double wheel_diameter = 0.1651;
    public double proportional_gain = 0.8;
    public double derivative_gain = 0.0;
    public double acceleration_gain = -0.1;
    public double velocity_intercept = 0.0;
    public double turn_gain = -0.01;

    //Closed Loop Chassis
    public double left_P = 0.5;
    public double left_I = 0.5;
    public double left_D = 0.5;
    public double right_P = 0.5;
    public double right_I = 0.5;
    public double right_D = 0.5;


    public SmartDashboardManager() {
        //Camera
        cam0 = CameraServer.getInstance().startAutomaticCapture(0);
        server = CameraServer.getInstance().getServer();
        cvsink0 = new CvSink("cam0cv");

        cvsink0.setSource(cam0);
        cvsink0.setEnabled(true);
        //Command Buttons
        SmartDashboard.putData("Execute Trajectory", new ExecuteTrajectory());  // I THINK THIS MAKES A BUTTON THAT WILL RUN THIS COMMAND
        //Numbers used in trajectory generation
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
        //Camera


        SmartDashboard.putNumber("NavX Angle", Robot.chassis.getHeading());
        SmartDashboard.putNumber("Left Encoder", Robot.chassis.getLeftEncoder());
        SmartDashboard.putNumber("Right Encoder", Robot.chassis.getRightEncoder());
        SmartDashboard.putData(Robot.chassis);
        //Motion Profile Testing
        //Numbers used in trajectory generation
        max_velocity = SmartDashboard.getNumber("Max Velocity (m/s)*", 9);
        max_acceleration = SmartDashboard.getNumber("Max Acceleration (m/s/s)*", 2);
        max_jerk = SmartDashboard.getNumber("Max Jerk (m/s/s/s)*", 60);
        //Numbers used in trajectory tracking
        wheel_diameter = SmartDashboard.getNumber("Wheel Diameter (m)", 0.1651);
        proportional_gain = SmartDashboard.getNumber("Proportional (Gain)", 1.4);
        derivative_gain = SmartDashboard.getNumber("Derivative (Gain)", 0.3);
        acceleration_gain = SmartDashboard.getNumber("Acceleration (Gain)", 0.0);
        wheel_base_width = SmartDashboard.getNumber("Wheelbase (m)", 0.653);
        velocity_intercept = SmartDashboard.getNumber("Velocity Intercept (output)", 0.05);
        turn_gain = SmartDashboard.getNumber("Turn Gain", -0.01);

//        //Elevator Encoder Output, One Way
//        SmartDashboard.putNumber("MM Sensor Position", Robot.elevator.getLeftEncoderPosition());
//        SmartDashboard.putNumber("MM Sensor Velocity", Robot.elevator.getLeftEncoderVelocity());
//        SmartDashboard.putNumber("MM Active Trajectory Position", Robot.elevator.getActiveTrajectoryPosition());
//        SmartDashboard.putNumber("MM Active Trajectory Velocity", Robot.elevator.getActiveTrajectoryVelocity());
//        SmartDashboard.putNumber("MM Closed Loop Error", Robot.elevator.getClosedLoopError());
//        SmartDashboard.putNumber("MM Motor Output", Robot.elevator.getMotorOutput());

    }
}
