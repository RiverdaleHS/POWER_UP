package com.team2915.POWER_UP.commands;

import com.team2915.POWER_UP.Robot;
import com.team2915.POWER_UP.TrajectoryGeneration.TrajectoryGenerator;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.command.Command;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;


public class ExecuteTrajectory extends Command {

    TankModifier tankModifier;
    EncoderFollower leftFollower;
    EncoderFollower rightFollower;
    Notifier notifier;

    Boolean hasRun = false;

    public ExecuteTrajectory(){
        requires(Robot.chassis);
        notifier = new Notifier(this::updateTracking);
    }


    @Override
    protected void execute() {
        super.execute();
        if (hasRun == false) {
            hasRun = true;
            TrajectoryGenerator trajgen = new TrajectoryGenerator();
            Trajectory trajectory = trajgen.generateTrajectory();
            tankModifier = new TankModifier(trajectory).modify(Robot.smartDashboardManager.wheel_base_width);
            leftFollower = new EncoderFollower(tankModifier.getLeftTrajectory());
            rightFollower = new EncoderFollower(tankModifier.getRightTrajectory());
            leftFollower.configurePIDVA(Robot.smartDashboardManager.proportional_gain, 0.0, Robot.smartDashboardManager.derivative_gain, Robot.smartDashboardManager.velocity_feedforward, Robot.smartDashboardManager.acceleration_feedforward);
            rightFollower.configurePIDVA(Robot.smartDashboardManager.proportional_gain, 0.0, Robot.smartDashboardManager.derivative_gain, Robot.smartDashboardManager.velocity_feedforward, Robot.smartDashboardManager.acceleration_feedforward);
            Robot.chassis.shiftLow();
            Robot.chassis.zeroNavX();
            leftFollower.configureEncoder(Robot.chassis.getLeftEncoder(), Robot.smartDashboardManager.encoder_ticks_per_rev, Robot.smartDashboardManager.wheel_diameter);
            rightFollower.configureEncoder(Robot.chassis.getRightEncoder(), Robot.smartDashboardManager.encoder_ticks_per_rev, Robot.smartDashboardManager.wheel_diameter);
            leftFollower.reset();
            rightFollower.reset();
            notifier.startPeriodic(trajectory.get(0).dt);
        }

    }
    private void updateTracking() {
        double leftOutput = leftFollower.calculate(Robot.chassis.getLeftEncoder());
        double rightOutput = rightFollower.calculate(Robot.chassis.getRightEncoder());
        double desiredHeading = Pathfinder.r2d(leftFollower.getHeading());
        double angleDifference = Pathfinder.boundHalfDegrees(desiredHeading - Robot.chassis.getHeading());
        double turn = Robot.smartDashboardManager.turn_gain * angleDifference; //This is a PD loop that modifies for turning.
        //System.out.println("left: " + leftOutput + " right: " + rightOutput + " turn: " + turn);
        leftOutput = leftOutput - turn;
        rightOutput = rightOutput + turn;

        if (leftOutput > 0){
            leftOutput = leftOutput + Robot.smartDashboardManager.velocity_intercept;
        }
        if (leftOutput < 0){
            leftOutput = leftOutput - Robot.smartDashboardManager.velocity_intercept;
        }
        if (rightOutput > 0){
            rightOutput = leftOutput + Robot.smartDashboardManager.velocity_intercept;
        }
        if (rightOutput < 0){
            rightOutput = leftOutput - Robot.smartDashboardManager.velocity_intercept;
        }

        Robot.chassis.setSpeed(-leftOutput, -rightOutput);
    }


    @Override
    protected boolean isFinished(){
        if (leftFollower.isFinished() && rightFollower.isFinished()){
            hasRun = false;
            notifier.stop();
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
        notifier.stop();
    }

}
