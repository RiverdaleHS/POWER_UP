package com.team2915.POWER_UP.commands;

import com.team2915.POWER_UP.Robot;
import com.team2915.POWER_UP.RobotMap;
import com.team2915.POWER_UP.TrajectoryGeneration.TrajectoryGenerator;
import edu.wpi.first.wpilibj.command.Command;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;


public class ExecuteTrajectory extends Command {

    TankModifier tankModifier;
    EncoderFollower leftFollower;
    EncoderFollower rightFollower;

    Boolean hasRun = false;



    public ExecuteTrajectory(){
        requires(Robot.chassis);
        //Make sure we are in low gear
        Robot.chassis.shiftLow();
        TrajectoryGenerator trajgen = new TrajectoryGenerator();
        Trajectory trajectory = trajgen.generateTrajectory();

        tankModifier = new TankModifier(trajectory).modify(RobotMap.ChassisMap.wheel_base_width);
        leftFollower = new EncoderFollower(tankModifier.getLeftTrajectory());
        rightFollower = new EncoderFollower(tankModifier.getRightTrajectory());

        // The first argument is the proportional gain. Usually this will be quite high
        // The second argument is the integral gain. This is unused for motion profiling
        // The third argument is the derivative gain. Tweak this if you are unhappy with the tracking of the trajectory
        // The fourth argument is the velocity ratio. This is 1 over the maximum velocity you provided in the
        //      trajectory configuration (it translates m/s to a -1 to 1 scale that your motors can read)
        // The fifth argument is your acceleration gain. Tweak this if you want to get to a higher or lower speed quicker
        leftFollower.configurePIDVA(RobotMap.ChassisMap.proportional_gain, 0.0, RobotMap.ChassisMap.derivative_gain, 1/RobotMap.ChassisMap.max_velocity, RobotMap.ChassisMap.acceleration_gain);
        rightFollower.configurePIDVA(RobotMap.ChassisMap.proportional_gain, 0.0, RobotMap.ChassisMap.derivative_gain, 1/RobotMap.ChassisMap.max_velocity, RobotMap.ChassisMap.acceleration_gain);

    }


    @Override
    protected void execute() {
        super.execute();
        if (hasRun == false) {
            hasRun = true;
            leftFollower.configureEncoder(Robot.chassis.getLeftEncoder(), RobotMap.ChassisMap.encoder_ticks_per_rev, RobotMap.ChassisMap.wheel_diameter);
            rightFollower.configureEncoder(Robot.chassis.getRightEncoder(), RobotMap.ChassisMap.encoder_ticks_per_rev, RobotMap.ChassisMap.wheel_diameter);
            leftFollower.reset();
            rightFollower.reset();
        }
        double leftOutput = leftFollower.calculate(Robot.chassis.getLeftEncoder());
        double rightOutput = rightFollower.calculate(Robot.chassis.getRightEncoder());

        double desiredHeading = Pathfinder.r2d(leftFollower.getHeading());
        double angleDifference = Pathfinder.boundHalfDegrees(desiredHeading - Robot.chassis.getHeading());
        double turn = 0.8 * (-1.0/80.0) * angleDifference; //No idea where these numbers come from
        Robot.chassis.setSpeed(-(leftOutput + turn), -(rightOutput - turn));
    }

    @Override
    protected boolean isFinished(){
        if (leftFollower.isFinished() && rightFollower.isFinished()){
            hasRun = false;
            return true;
        }
        return false;
    }

    @Override
    protected void interrupted() {
        super.interrupted();
        end();
    }

    @Override
    protected void end() {
        super.end();
        Robot.chassis.stop();
    }

}
