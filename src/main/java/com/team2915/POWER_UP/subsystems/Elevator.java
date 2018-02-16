package com.team2915.POWER_UP.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.team2915.POWER_UP.Robot;
import com.team2915.POWER_UP.RobotMap;
import com.team2915.POWER_UP.commands.ManualElevator;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem {

    private TalonSRX left = new TalonSRX(RobotMap.ElevaotrMap.leftMotor);
    private TalonSRX right = new TalonSRX(RobotMap.ElevaotrMap.rightMotor);

//    private DigitalInput topA = new DigitalInput(RobotMap.ElevaotrMap.limitTopA);
//    private DigitalInput topB = new DigitalInput(RobotMap.ElevaotrMap.limitTopB);
//    private DigitalInput bottomA = new DigitalInput(RobotMap.ElevaotrMap.limitBottomA);
//    private DigitalInput bottomB = new DigitalInput(RobotMap.ElevaotrMap.limitBottomB);

    public Elevator(){
//        left.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
//        left.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, 10);
//        left.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, 10);
//        left.configNominalOutputForward(0, 10);
//        left.configNominalOutputReverse(0, 10);
//        left.configPeakOutputForward(1, 10);
//        left.configPeakOutputReverse(-1, 10);
//        configPIDFVA();
        right.setInverted(true);
        right.follow(left);
    }

    public void configPIDFVA(){
//        left.selectProfileSlot(0, 0);
//        left.config_kP(0, 0, 10);
//        left.config_kI(0, 0, 10);
//        left.config_kD(0, 0,  10);
//        left.config_kF(0, 0, 10);
//        left.configMotionCruiseVelocity(100, 10);
//        left.configMotionAcceleration(100, 10);
    }
//
//    public boolean isUp(){
//        if(topA.get() | topB.get()){
//            return true;
//        }
//        return false;
//    }
//
//    public boolean isDown(){
//        if(bottomA.get() | bottomB.get()){
//            return true;
//        }
//        return false;
//    }

    public void initDefaultCommand(){
        setDefaultCommand(new ManualElevator());
    }

//    public void setMotionMagicSetpoint(double setpoint){
//        left.set(ControlMode.MotionMagic, setpoint);
//    }

    public void setPercentOutput(double percent){
        left.set(ControlMode.PercentOutput, percent);
    }
//
//    //Sensor outputs for tuning in Smart Dashboard
//    public double getLeftEncoderPosition(){
//        return left.getSelectedSensorPosition(0);
//    }
//
//    public double getLeftEncoderVelocity(){
//        return left.getSelectedSensorVelocity(0);
//    }
//
//    public double getActiveTrajectoryPosition(){
//        return left.getActiveTrajectoryPosition();
//    }
//
//    public double getActiveTrajectoryVelocity(){
//        return left.getActiveTrajectoryVelocity();
//    }
//
//    public double getClosedLoopError(){
//        return left.getClosedLoopError(0);
//    }
//
//    public double getMotorOutput(){
//        return left.getMotorOutputPercent();
//    }

}
